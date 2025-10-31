package backend

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UsermanageController {

    UsermanageService usermanageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond usermanageService.list(params), model:[usermanageCount: usermanageService.count()]
    }

    def show(Long id) {
        respond usermanageService.get(id)
    }

    def create() {
        respond new Usermanage(params)
    }

    def save(Usermanage usermanage) {
        if (usermanage == null) {
            notFound()
            return
        }

        try {
            usermanageService.save(usermanage)
        } catch (ValidationException e) {
            respond usermanage.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usermanage.label', default: 'Usermanage'), usermanage.id])
                redirect usermanage
            }
            '*' { respond usermanage, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond usermanageService.get(id)
    }

    def update(Usermanage usermanage) {
        if (usermanage == null) {
            notFound()
            return
        }

        try {
            usermanageService.save(usermanage)
        } catch (ValidationException e) {
            respond usermanage.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usermanage.label', default: 'Usermanage'), usermanage.id])
                redirect usermanage
            }
            '*'{ respond usermanage, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        usermanageService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usermanage.label', default: 'Usermanage'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usermanage.label', default: 'Usermanage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
