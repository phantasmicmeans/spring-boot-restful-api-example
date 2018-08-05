package com.sw.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sw.project.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {
	
	
	@Query(value = "SELECT * FROM project WHERE code=?1",  nativeQuery = true)
	Project getProject(String code); 

	
}

/*
Interface JpaRepository<T,ID>

 *
 * CrudRepository<T,ID>, PagingAndSortingRepository<T,ID>, QueryByExampleExecutor<T>, Repository<T,ID>
 *

All MethodsInstance MethodsAbstract Methods
Modifier and Type	Method and Description

void				deleteAllInBatch()
Deletes 			all entities in a batch call.

void				deleteInBatch(Iterable<T> entities)
Deletes 			the given entities in a batch which means it will create a single Query.

List<T>				findAll() 

<S extends T>
List<S>				findAll(Example<S> example) 

<S extends T>
List<S>				findAll(Example<S> example, Sort sort) 

List<T>				findAll(Sort sort) 

List<T>				findAllById(Iterable<ID> ids) 

void				flush()
					Flushes all pending changes to the database.
					
T					getOne(ID id)
					Returns a reference to the entity with the given identifier.
					
<S extends T>
List<S>				saveAll(Iterable<S> entities) 

<S extends T>
S					saveAndFlush(S entity)
					Saves an entity and flushes changes instantly.

*/