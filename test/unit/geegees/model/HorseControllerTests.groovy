package geegees.model



import org.junit.*
import grails.test.mixin.*

@TestFor(HorseController)
@Mock(Horse)
class HorseControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/horse/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.horseInstanceList.size() == 0
        assert model.horseInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.horseInstance != null
    }

    void testSave() {
        controller.save()

        assert model.horseInstance != null
        assert view == '/horse/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/horse/show/1'
        assert controller.flash.message != null
        assert Horse.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/horse/list'

        populateValidParams(params)
        def horse = new Horse(params)

        assert horse.save() != null

        params.id = horse.id

        def model = controller.show()

        assert model.horseInstance == horse
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/horse/list'

        populateValidParams(params)
        def horse = new Horse(params)

        assert horse.save() != null

        params.id = horse.id

        def model = controller.edit()

        assert model.horseInstance == horse
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/horse/list'

        response.reset()

        populateValidParams(params)
        def horse = new Horse(params)

        assert horse.save() != null

        // test invalid parameters in update
        params.id = horse.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/horse/edit"
        assert model.horseInstance != null

        horse.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/horse/show/$horse.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        horse.clearErrors()

        populateValidParams(params)
        params.id = horse.id
        params.version = -1
        controller.update()

        assert view == "/horse/edit"
        assert model.horseInstance != null
        assert model.horseInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/horse/list'

        response.reset()

        populateValidParams(params)
        def horse = new Horse(params)

        assert horse.save() != null
        assert Horse.count() == 1

        params.id = horse.id

        controller.delete()

        assert Horse.count() == 0
        assert Horse.get(horse.id) == null
        assert response.redirectedUrl == '/horse/list'
    }
}
