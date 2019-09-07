package com.example.agriapp_t.data.repository;

import android.app.Application;
import android.os.AsyncTask;


import com.example.agriapp_t.data.dao.CropDao;
import com.example.agriapp_t.data.database.Database;
import com.example.agriapp_t.data.model.Crop;
import com.example.agriapp_t.data.model.table.SoilAnalysisTable;
import com.example.agriapp_t.ui.custom_listener.OnCropClassDataFetch;
import com.example.agriapp_t.ui.custom_listener.OnDataFetchListener;
import com.example.agriapp_t.ui.custom_listener.OnNutrientDataFetched;

import java.util.List;
import java.util.concurrent.Executors;

public class CropRepository {
    Database database;
    public CropRepository (Application application) {
         database = Database.getInstance(application);
    }

    public void test () {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<String> allDistinctCropGroup = database.cropSeasonDao().getAllDistinctCropGroup();
                System.out.println(allDistinctCropGroup.size() + " -> " + allDistinctCropGroup.toString());
                List<Integer> allAnalysis = database.soilAnalysisDao().getAllAnalysis();
                System.out.println(allAnalysis.size());
            }
        });
    }

    public void getAllCrops (OnDataFetchListener listener) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<String> allDistinctCropGroup = database.cropSeasonDao().getAllDistinctCropGroup();
                System.out.println(allDistinctCropGroup.size() + " -> " + allDistinctCropGroup.toString());
                List<Integer> allAnalysis = database.soilAnalysisDao().getAllAnalysis();
                List<String> allCrop = database.cropVarietyDao().getAllCropSeason();
                System.out.println(allCrop.size());
                listener.onAfterFetch(allCrop, 1);
            }
        });
    }

    public void getAllTexture (OnDataFetchListener listener) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<String> allCrop = database.soilTextureDao().getAllTexture();
                System.out.println(allCrop.size());
                listener.onAfterFetch(allCrop, 2);
            }
        });
    }

    public void getNutrientRecommendation (int uniqueId, String status, String nutrient, OnNutrientDataFetched dataFetched) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                SoilAnalysisTable analysisTable = database.soilAnalysisDao()
                        .getAnalysisInterpretationByTestClass(uniqueId, status, nutrient);
                System.out.println(analysisTable.toString());
                System.out.println(analysisTable.getAnalysisNutrientInterval() + analysisTable.getAnalysisNutrientUpperLimit());
                dataFetched.onAfterDataFetched(analysisTable.getAnalysisNutrientUpperLimit(), analysisTable.getAnalysisNutrientInterval(), nutrient);
            }
        });
    }

    public void getCropClass (String crop, OnCropClassDataFetch dataFetch) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Integer> data = database.cropVarietyDao().getCropClass(crop);

                dataFetch.onDataFetch(data.get(0));
            }
        });
    }

//    public void insert (Crop crop) {
//        new InsertAsyncTask(cropDao).execute(crop);
//    }
//
//    public List<Crop> getAllCrop() {
//        return this.crops;
//    }
//
//    private static class InsertAsyncTask extends AsyncTask<Crop, Void, Void> {
//        private CropDao cropDao;
//
//        private InsertAsyncTask(CropDao cropDao) {
//            this.cropDao = cropDao;
//        }
//
//        @Override
//        protected Void doInBackground(Crop... crops) {
//            cropDao.insert(crops[0]);
//            return null;
//        }
//    }
}
