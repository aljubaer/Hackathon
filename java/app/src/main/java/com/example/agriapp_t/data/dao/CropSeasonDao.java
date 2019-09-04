package com.example.agriapp_t.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CropSeasonDao {

    @Query("SELECT season FROM crop_season " +
            "WHERE crop_name=" +
            "(SELECT crop_name FROM crop " +
            "WHERE crop_group = :cropGroup) ")
    List<String> getAllSeasonByCropGroup(String cropGroup);

    @Query("SELECT crop.crop_group FROM crop,crop_season " +
            "WHERE crop_season.season = :season")
    String getCropGroupBySeason(String season);
}
