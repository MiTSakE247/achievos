package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.dto.request.LevelRequest;
import com.mdx.achievos.entity.Level;

import java.util.List;

public interface LevelService {

    List<Level> getAllLevels();

    String addLevel(LevelRequest request);

    String updateLevel(Long levelId, LevelRequest request);

    Level getLevelById(Long levelId);
}
