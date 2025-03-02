package com.mdx.achievos.service;

import com.mdx.achievos.dto.LevelRequest;

public interface AdminService {

    String getAllLevels(Long userId, LevelRequest request);
}
