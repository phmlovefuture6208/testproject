package backend

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UsermanageServiceSpec extends Specification {

    UsermanageService usermanageService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Usermanage(...).save(flush: true, failOnError: true)
        //new Usermanage(...).save(flush: true, failOnError: true)
        //Usermanage usermanage = new Usermanage(...).save(flush: true, failOnError: true)
        //new Usermanage(...).save(flush: true, failOnError: true)
        //new Usermanage(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //usermanage.id
    }

    void "test get"() {
        setupData()

        expect:
        usermanageService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Usermanage> usermanageList = usermanageService.list(max: 2, offset: 2)

        then:
        usermanageList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        usermanageService.count() == 5
    }

    void "test delete"() {
        Long usermanageId = setupData()

        expect:
        usermanageService.count() == 5

        when:
        usermanageService.delete(usermanageId)
        sessionFactory.currentSession.flush()

        then:
        usermanageService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Usermanage usermanage = new Usermanage()
        usermanageService.save(usermanage)

        then:
        usermanage.id != null
    }
}
