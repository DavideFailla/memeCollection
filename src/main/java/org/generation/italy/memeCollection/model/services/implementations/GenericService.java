package org.generation.italy.memeCollection.model.services.implementations;

import org.generation.italy.memeCollection.model.data.abstractions.GenericRepository;
import org.generation.italy.memeCollection.model.data.exceptions.DataException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class GenericService<T> {
    private GenericRepository<T> repo;
    public GenericService(GenericRepository<T> repo){
        this.repo = repo;
    }

    public List<T> findAll() throws DataException {
        return repo.findAll();
    }


    public Optional<T> findById(long id) throws DataException {
        return repo.findById(id);
    }


    @Transactional
    public T create(T entity) throws DataException {
        return repo.save(entity);
    }

    @Transactional
    public void update(T entity) throws DataException {
        repo.save(entity);
    }

    @Transactional
    public void deleteById(long id) throws DataException {
        repo.deleteById(id);
    }
}
