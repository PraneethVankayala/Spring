## Bean Scopes ##

- Scope refers to the lifecycle of bean
- How long bean will leave?
- How many instances are created?
- How is the bean shared?

Default scope for bean is singleton

**What is a Singleton**
- Spring container creates only one instance of the bean, by default
- It is cached in memory
- All requests for the bean will return a shared reference to the same bean

We can specify the scope 
- `< bean id = "myCoach" class="Spring.BeanScopes.TrackCoach" scope="singleton"/>`

**Singleton**: Creates a single shared instance of the bean. Default scope

**Prototype**: Creates a new bean instance for each container request. This is used to keep track of stateful data

**Request**: Scoped to an HTTP web request. Only used for web apps

**Session**: Scoped to an HTTP web session. Only used for web apps.

**Global-Session**: Scoped to a global HTTP web session. Only used for web apps.


### Custom Init Methods/Hooks: ###

- You can add custom code during bean initialization
	- Calling custom business logic methods
	- Setting up handles to resources(db, sockets, file etc).
	`<bean id="myCoach" class = "" init-method="initMethodName"/>`
- You can add custom code during bean destruction
	- Calling custom business logic method
	- Clean up handles to resources(db, sockets, files etc)
	`<bean id="myCoach" class = "" destroy-method="destroyMethodName"/>`

**Access modifier** <br /> The method can have any access modifier (public, protected, private)

**Return type** <br />
The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.

**Method name** <br />
The method can have any method name.

**Arguments** <br />
The method can not accept any arguments. The method should be no-arg.
