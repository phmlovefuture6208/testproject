package backend

import grails.gorm.services.Service

@Service(Usermanage)
interface UsermanageService {

    Usermanage get(Serializable id)

    List<Usermanage> list(Map args)

    Long count()

    void delete(Serializable id)

    Usermanage save(Usermanage usermanage)

}