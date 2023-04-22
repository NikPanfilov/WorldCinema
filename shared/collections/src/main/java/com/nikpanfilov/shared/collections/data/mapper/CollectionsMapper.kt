package com.nikpanfilov.shared.collections.data.mapper

import com.nikpanfilov.shared.collections.data.dto.CollectionDto
import com.nikpanfilov.shared.collections.data.dto.CollectionFormDto
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.CollectionForm

internal fun CollectionForm.toDto() = CollectionFormDto(name = name)

internal fun CollectionDto.toEntity() = Collection(
	collectionId = collectionId,
	iconRes = name.substringBefore("//").toInt(),
	name = name.substringAfter("//")
)