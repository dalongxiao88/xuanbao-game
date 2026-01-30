package org.come.mapper;

import org.come.entity.Species;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface SpeciesMapper
{
    List<Species> getAllSpecies();
}
