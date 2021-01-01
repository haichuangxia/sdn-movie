package com.neo4j.example.movie;

import com.neo4j.example.movie.domains.AbstractNodeEntity;
import com.neo4j.example.movie.domains.Role;
import com.neo4j.example.movie.repositories.NodeRepository;
import com.neo4j.example.movie.repositories.PageableRepository;
import com.neo4j.example.movie.repositories.RelationshipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Iterator;

@SpringBootTest
class MovieApplicationTests {
	@Autowired
	private RelationshipRepository relationshipRepository;
	@Autowired
	private NodeRepository nodeRepository;
	@Autowired
	private PageableRepository pageableRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void nodeRepositoryTest() {
		Iterator obj = null;
		if ((obj = nodeRepository.getAllNotes().iterator()).hasNext()) {
			System.out.println(obj.toString());
		}
	}

	@Test
	public void getRelationship() {
		Iterable<Role> roleIterable = relationshipRepository.getAllRelationships();
//		if()
		System.out.println(relationshipRepository.getAllRelationships());
	}
//	@Test
//	public void Pagetest(){
//		Page<AbstractNodeEntity> nodes= (Page<AbstractNodeEntity>) pageableRepository.findAll(PageRequest.of(1,20));
//		System.out.println(nodes);
//	}
}