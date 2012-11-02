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

    void testList() {

        def model = controller.list()

        assert model.raceDayInstanceList.size() == 0
        assert model.raceDayInstanceTotal == 0
    }
}
