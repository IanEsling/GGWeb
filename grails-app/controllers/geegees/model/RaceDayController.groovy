package geegees.model

import org.springframework.dao.DataIntegrityViolationException
import org.joda.time.LocalDate
import geegees.web.EmailPresentableRaceDay

class RaceDayController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [raceDayInstanceList: RaceDay.list(params), raceDayInstanceTotal: RaceDay.count()]
    }

    def create() {
        [raceDayInstance: new RaceDay(params)]
    }

    def save() {
        def raceDayInstance = new RaceDay(params)
        if (!raceDayInstance.save(flush: true)) {
            render(view: "create", model: [raceDayInstance: raceDayInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'raceDay.label', default: 'RaceDay'), raceDayInstance.id])
        redirect(action: "show", id: raceDayInstance.id)
    }

    def email(Long id){
//        RaceDay raceDay = RaceDay.get(id)
//        render(view: "/email/raceDay", model: [raceDay: new EmailPresentableRaceDay(raceDay)])
        sendMail {
            to "ian.esling@gmail.com"
            from "GeeGees@GeeGees.com"
            subject "Email From GeeGees!"
            body (view: "/email/raceDay", model:[raceDay: new EmailPresentableRaceDay(raceDay)])
        }
    }


    def show(Long id) {
        def raceDayInstance = RaceDay.get(id)
        if (!raceDayInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'raceDay.label', default: 'RaceDay'), id])
            redirect(action: "list")
            return
        }

        [raceDayInstance: raceDayInstance]
    }

    def edit(Long id) {
        def raceDayInstance = RaceDay.get(id)
        if (!raceDayInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'raceDay.label', default: 'RaceDay'), id])
            redirect(action: "list")
            return
        }

        [raceDayInstance: raceDayInstance]
    }

    def update(Long id, Long version) {
        def raceDayInstance = RaceDay.get(id)
        if (!raceDayInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'raceDay.label', default: 'RaceDay'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (raceDayInstance.version > version) {
                raceDayInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'raceDay.label', default: 'RaceDay')] as Object[],
                        "Another user has updated this RaceDay while you were editing")
                render(view: "edit", model: [raceDayInstance: raceDayInstance])
                return
            }
        }

        raceDayInstance.properties = params

        if (!raceDayInstance.save(flush: true)) {
            render(view: "edit", model: [raceDayInstance: raceDayInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'raceDay.label', default: 'RaceDay'), raceDayInstance.id])
        redirect(action: "show", id: raceDayInstance.id)
    }

    def delete(Long id) {
        def raceDayInstance = RaceDay.get(id)
        if (!raceDayInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'raceDay.label', default: 'RaceDay'), id])
            redirect(action: "list")
            return
        }

        try {
            raceDayInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'raceDay.label', default: 'RaceDay'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'raceDay.label', default: 'RaceDay'), id])
            redirect(action: "show", id: id)
        }
    }
}
