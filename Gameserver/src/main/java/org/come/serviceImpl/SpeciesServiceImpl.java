package org.come.serviceImpl;

import org.come.entity.Species;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.SpeciesMapper;
import org.come.service.ISpeciesService;

public class SpeciesServiceImpl implements ISpeciesService
{
    private SpeciesMapper speciesMapper;
    
    public SpeciesServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.speciesMapper = (SpeciesMapper)ctx.getBean("speciesMapper");
    }
    
    @Override
    public List<Species> getAllSpecies() {
        List<Species> species = this.speciesMapper.getAllSpecies();
        return species;
    }
}
