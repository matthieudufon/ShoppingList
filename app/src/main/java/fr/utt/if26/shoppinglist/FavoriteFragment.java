package fr.utt.if26.shoppinglist;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.utt.if26.shoppinglist.entities.embedded.AlimentAndCompose;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;

public class FavoriteFragment extends Fragment {

    private AlimentViewModel alimentViewModel;
    private Map<String, Integer> mapAliments;
    private List<String> sortedAliments;
    private Map<String,Integer> sorted;
    private BarChart barChart;
    private BarData barData;
    private BarDataSet barDataSet;
    private ArrayList barEntriesArrayList;

    public FavoriteFragment() {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_favorite, container, false);

        barChart = result.findViewById(R.id.fragment_favorite_hbc);

        sortedAliments = new ArrayList<String>();
        createHorizontalBarChart();

        mapAliments = new HashMap<String, Integer>();
        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);
        alimentViewModel.getAlimentAndCompose().observe(getViewLifecycleOwner(), options -> {
            for (AlimentAndCompose alimentAndCompose : options) {
                mapAliments.put(alimentAndCompose.aliment.getNom(),
                        mapAliments.getOrDefault(alimentAndCompose.aliment.getCategorie(), 0) + alimentAndCompose.composes.get(0).getQuantite()
                );
            }
            sortedAliments = mapAliments.entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .limit(10)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            createHorizontalBarChart();
        });

        return result;
    }

    private void createHorizontalBarChart() {
        System.out.println(sortedAliments);
        barEntriesArrayList = new ArrayList();
        for (int i = 0; i < sortedAliments.size(); i++) {
            barEntriesArrayList.add(new BarEntry((float) sortedAliments.size() - 1 - i, mapAliments.get(sortedAliments.get(i))));
        }
        System.out.println(barEntriesArrayList);
        barDataSet = new BarDataSet(barEntriesArrayList, "Aliments les plus consommés");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.setScaleEnabled(false);
        barChart.setDrawBorders(true);
        barDataSet.setColor(Color.BLUE);
        barDataSet.setValueTextColor(Color.TRANSPARENT);
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        Collections.reverse(sortedAliments);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(sortedAliments));
        Collections.reverse(sortedAliments);
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextSize(14f);
        xAxis.setTextColor(Color.LTGRAY);
        xAxis.setLabelCount(10);
        xAxis.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        xAxis.setDrawAxisLine(false);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.animate();
        barChart.invalidate();
    }

}