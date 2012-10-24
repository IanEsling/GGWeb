package geegees.model

import org.springframework.dao.DataIntegrityViolationException

class HorseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [horseInstanceList: Horse.list(params), horseInstanceTotal: Horse.count()]
    }

    def create() {
        [horseInstance: new Horse(params)]
    }

    def save() {
        def horseInstance = new Horse(params)
        if (!horseInstance.save(flush: true)) {
            render(view: "create", model: [horseInstance: horseInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'horse.label', default: 'Horse'), horseInstance.id])
        redirect(action: "show", id: horseInstance.id)
    }

    def show(Long id) {
        def horseInstance = Horse.get(id)
        if (!horseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'horse.label', default: 'Horse'), id])
            redirect(action: "list")
            return
        }

        [horseInstance: horseInstance]
    }

    def edit(Long id) {
        def horseInstance = Horse.get(id)
        if (!horseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'horse.label', default: 'Horse'), id])
            redirect(action: "list")
            return
        }

        [horseInstance: horseInstance]
    }

    def update(Long id, Long version) {
        def horseInstance = Horse.get(id)
        if (!horseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'horse.label', default: 'Horse'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (horseInstance.version > version) {
                horseInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'horse.label', default: 'Horse')] as Object[],
                        "Another user has updated this Horse while you were editing")
                render(view: "edit", model: [horseInstance: horseInstance])
                return
            }
        }

        horseInstance.properties = params

        if (!horseInstance.save(flush: true)) {
            render(view: "edit", model: [horseInstance: horseInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'horse.label', default: 'Horse'), horseInstance.id])
        redirect(action: "show", id: horseInstance.id)
    }

    def delete(Long id) {
        def horseInstance = Horse.get(id)
        if (!horseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'horse.label', default: 'Horse'), id])
            redirect(action: "list")
            return
        }

        try {
            horseInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'horse.label', default: 'Horse'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'horse.label', default: 'Horse'), id])
            redirect(action: "show", id: id)
        }
    }
}
