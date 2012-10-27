package geegees.model



import org.junit.*
import grails.test.mixin.*

@TestFor(RaceDayController)
@Mock(RaceDay)
class RaceDayControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/raceDay/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.raceDayInstanceList.size() == 0
        assert model.raceDayInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.raceDayInstance != null
    }

    void testSave() {
        controller.save()

        assert model.raceDayInstance != null
        assert view == '/raceDay/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/raceDay/show/1'
        assert controller.flash.message != null
        assert RaceDay.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/raceDay/list'

        populateValidParams(params)
        def raceDay = new RaceDay(params)

        assert raceDay.save() != null

        params.id = raceDay.id

        def model = controller.show()

        assert model.raceDayInstance == raceDay
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/raceDay/list'

        populateValidParams(params)
        def raceDay = new RaceDay(params)

        assert raceDay.save() != null

        params.id = raceDay.id

        def model = controller.edit()

        assert model.raceDayInstance == raceDay
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/raceDay/list'

        response.reset()

        populateValidParams(params)
        def raceDay = new RaceDay(params)

        assert raceDay.save() != null

        // test invalid parameters in update
        params.id = raceDay.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/raceDay/edit"
        assert model.raceDayInstance != null

        raceDay.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/raceDay/show/$raceDay.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        raceDay.clearErrors()

        populateValidParams(params)
        params.id = raceDay.id
        params.version = -1
        controller.update()

        assert view == "/raceDay/edit"
        assert model.raceDayInstance != null
        assert model.raceDayInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/raceDay/list'

        response.reset()

        populateValidParams(params)
        def raceDay = new RaceDay(params)

        assert raceDay.save() != null
        assert RaceDay.count() == 1

        params.id = raceDay.id

        controller.delete()

        assert RaceDay.count() == 0
        assert RaceDay.get(raceDay.id) == null
        assert response.redirectedUrl == '/raceDay/list'
    }
}
