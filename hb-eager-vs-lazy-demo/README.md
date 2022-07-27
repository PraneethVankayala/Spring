## Eager and Lazy Loading ##

**Important Database Concepts**
- Primary Key and foriegn Key
	- Primary Key: Uniquely identifies a row in a table
	- Foriegn key: It is a field in one table refers to the primary key of the other table, It's main purpose is to preserve relationship between tables. Referential Integrity
- Cascade
 	- Apply the same operation to related entities
	- if we delete instructor we should also delete instructor detail( CASCADE_DELETE)
	- This cascade depends on the usecaase

**Fetch types: Eager vs Lazy loading**
- When we fetch/retrieve data should we retrieve everything?
	- Eager will retrieve everything
	- Lazy will retrieve on request
- Only load data which is absolutely needed, Prefer Lazy loading over eager loading
- When you define the mapping relationship, you can specify EAGER or LAZY
  ``` java
    public class Instructor{
      @OneToMany(fetch=FetchType.LAZY, mappedBy="instructor")
      private List<Course> courses;
    }
  ```
- Default fetch types
	- @OneToOne: FetchType.EAGER
	- @OneToMany: FetchType.LAZY
	- @ManyToOne: FetchType.EAGER
	- @ManyToMany: FetchType.LAZY

- Retrieve lazy data using
	- session.get and call appropriate getter methods
	- Hibernate query with HQL
	 ``` java
      int theId = 1;
      Query<Instructor> query = session.createQuery("select i from Instructor i "+
                              "JOIN FETCH i.courses "+
                              "where i.id=:theInstructorId",Instructor.class);

      query.setParameter("theInstructorId", theId);

      Instructor tmpIns = query.getSingleResult();
    ```
  
- Lazy loading requires an open hibernate connection, need an connection to database to retrieve data. If session is closed hibernate will throw an exception.
Entity Lifecycle
- Detach: If entity is detached it is not associated with a Hibernate session
- Merge: If instance is detached from session, then merge will reattach to session
- Persist: Transitions new instances to managed state. Next flush/commit will save in db
- Remove: Transitions managed entity to be removed. Next flush/commit will delete from db
- Refresh: Reload/synch object with data from db. Prevents stale data.

**Relationships:**
- Uni-Directional
- Bi-Directional
	- To use Bi-Directional we can keep the existing database schema
	- Simply Update the java code.

