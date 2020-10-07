package com.recipeee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRV;
    RecyclerViewAdapter myAdapters;
    List<Recipes> recipes1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipes1 = new ArrayList<>();

        recipes1.add(new Recipes("DonutBall", "1 c. whole milk" +
                "1/2tsp.kosher salt", "Method", "\n" + "Grease a large bowl with cooking spray and set aside.",
                R.drawable.donutball));

        recipes1.add(new Recipes("Mille Crepe", "6 tbs butter, sliced, 3 cups milk, 6 eggs, 1 1/2 cups flour, 6 tbs sugqar, 1/8 tsp salt",
                "Method", "\n" + "Sift the flour into a large mixing bowl then add sugar and salt.", R.drawable.millecrepe));

        recipes1.add(new Recipes("Homemade Lemonade", "8 cups water, 1 1/2 cups fresh squeezed lemon juice, 1 1/2 cups sugar",
                "Method", "\n" + "Place sugar and 2 cups of water in a sauce pan. Heat until sugar dissolves Pour sugar water, lemon juice and remaining water into a gallon pitcher. Chill for several hours or overnight.", R.drawable.lemonade));

        recipes1.add(new Recipes("Red Velvet Cheesecake Mini Pie", "36 chocolate wafer cookies, crushed, 4 1/2 tablespoons butter, melted, 1 teaspoon corn syrup or honey",
                "Method", "\n" + "In small bowl, mix Crust ingredients. In large bowl, beat cream cheese with electric mxer on medium speed until smooth. Bake 30 to 35 minutes or until centers are firm.", R.drawable.velvetpie));

        myRV = (RecyclerView) findViewById(R.id.recyclerView_id);
        myAdapters = new RecyclerViewAdapter(this, recipes1);
        myRV.setLayoutManager(new GridLayoutManager(this, 1));
        myRV.setAdapter(myAdapters);

    }
}