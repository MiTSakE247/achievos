package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.LevelRequest;
import com.mdx.achievos.entity.Level;
import com.mdx.achievos.repo.LevelRepo;
import com.mdx.achievos.service.interfaces.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepo levelRepo;

    public LevelServiceImpl(LevelRepo levelRepo) {
        this.levelRepo = levelRepo;
    }

    @Override
    public List<Level> getAllLevels() {
        return levelRepo.findAll();
    }

    @Override
    public String addLevel(LevelRequest request) {
        return "";
    }

    @Override
    public String updateLevel(Long levelId, LevelRequest request) {
        return "";
    }

    @Override
    public Level getLevelById(Long levelId) {
        return null;
    }
}
