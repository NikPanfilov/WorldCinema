package com.nikpanfilov.main.data.mapper

import com.nikpanfilov.main.data.dto.CoverDto
import com.nikpanfilov.main.domain.entity.Cover

internal fun CoverDto.toEntity() = Cover(backgroundImage = backgroundImage, foregroundImage = foregroundImage)