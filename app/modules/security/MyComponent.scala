package modules.security


import javax.inject.Inject
import play.api.inject.ApplicationLifecycle

trait MyComponent

class MyComponentImpl extends MyComponent {
    println("============ SecurityModule startup.")
}
