package com.example.bodyworks.views.nutritionalValues

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bodyworks.R
import com.example.bodyworks.databinding.ActivityFoodNutritionalValuesBinding
import com.example.bodyworks.views.nutritionalValues.adapter.FoodDataAdapter
import com.example.bodyworks.views.nutritionalValues.model.FoodDataModel

class FoodNutritionalValues : AppCompatActivity() {
    private lateinit var binding: ActivityFoodNutritionalValuesBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(it) }!!
        val themeColor = sharedPreferences.getString("Current Theme", getString(R.string.red))
        val color = changeTheme(themeColor)

        binding = ActivityFoodNutritionalValuesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.nutritionalValueToolBar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Initialize your adapter and set it to the RecyclerView
        val adapter = FoodDataAdapter(mutableListOf());
        binding.foodRecyclerView.adapter = adapter;

        //string array for plans
        val foodCategories = resources.getStringArray(R.array.food_categories)
        val foodCategoriesAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, foodCategories)
        binding.foodCategoryDropDown.setAdapter(foodCategoriesAdapter)

        //recycler view
        binding.foodRecyclerView.setHasFixedSize(true)
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this)

        // creating lists
        val fruitsList = mutableListOf(
            FoodDataModel(
                getString(R.string.apple_name),
                R.drawable.apple,
                getString(R.string.apple_description)
            ),
            FoodDataModel(
                getString(R.string.banana_name),
                R.drawable.banana,
                getString(R.string.banana_description)
            ),
            FoodDataModel(
                getString(R.string.orange_name),
                R.drawable.orange,
                getString(R.string.orange_description)
            ),
            FoodDataModel(
                getString(R.string.strawberries_name),
                R.drawable.strawberries,
                getString(R.string.strawberries_description)
            ),
            FoodDataModel(
                getString(R.string.pineapple_name),
                R.drawable.pineapple,
                getString(R.string.pineapple_description)
            ),
            FoodDataModel(
                getString(R.string.grapes_name),
                R.drawable.grapes,
                getString(R.string.grapes_description)
            ),
            FoodDataModel(
                getString(R.string.mango_name),
                R.drawable.mango,
                getString(R.string.mango_description)
            ),
            FoodDataModel(
                getString(R.string.watermelon_name),
                R.drawable.watermelon,
                getString(R.string.watermelon_description)
            ),
            FoodDataModel(
                getString(R.string.peach_name),
                R.drawable.peach,
                getString(R.string.peach_description)
            ),
            FoodDataModel(
                getString(R.string.kiwi_name),
                R.drawable.kiwi,
                getString(R.string.kiwi_description)
            )
        )

        // Proteins
        val proteinsList = mutableListOf(
            FoodDataModel(
                getString(R.string.chicken_breast_name),
                R.drawable.chicken_breast,
                getString(R.string.chicken_breast_description)
            ),
            FoodDataModel(
                getString(R.string.salmon_name),
                R.drawable.salmon,
                getString(R.string.salmon_description)
            ),
            FoodDataModel(
                getString(R.string.egg_name),
                R.drawable.egg,
                getString(R.string.egg_description)
            ),
            FoodDataModel(
                getString(R.string.tofu_name),
                R.drawable.tofu,
                getString(R.string.tofu_description)
            ),
            FoodDataModel(
                getString(R.string.turkey_name),
                R.drawable.turkey,
                getString(R.string.turkey_description)
            ),
            FoodDataModel(
                getString(R.string.beef_name),
                R.drawable.beef,
                getString(R.string.beef_description)
            ),
            FoodDataModel(
                getString(R.string.pork_name),
                R.drawable.pork,
                getString(R.string.pork_description)
            ),
            FoodDataModel(
                getString(R.string.lentils_name),
                R.drawable.lentils,
                getString(R.string.lentils_description)
            ),
            FoodDataModel(
                getString(R.string.chickpeas_name),
                R.drawable.chickpeas,
                getString(R.string.chickpeas_description)
            ),
            FoodDataModel(
                getString(R.string.quinoa_name),
                R.drawable.quinoa,
                getString(R.string.quinoa_description)
            )
        )

        // Grains
        val grainsList = mutableListOf(
            FoodDataModel(
                getString(R.string.brown_rice_name),
                R.drawable.brown_rice,
                getString(R.string.brown_rice_description)
            ),
            FoodDataModel(
                getString(R.string.quinoa_name),
                R.drawable.quinoa,
                getString(R.string.quinoa_description_v2)
            ),
            FoodDataModel(
                getString(R.string.barley_name),
                R.drawable.barley,
                getString(R.string.barley_description)
            ),
            FoodDataModel(
                getString(R.string.oats_name),
                R.drawable.oats,
                getString(R.string.oats_description)
            ),
            FoodDataModel(
                getString(R.string.whole_wheat_bread_name),
                R.drawable.whole_wheat_bread,
                getString(R.string.whole_wheat_bread_description)
            ),
            FoodDataModel(
                getString(R.string.couscous_name),
                R.drawable.couscous,
                getString(R.string.couscous_description)
            ),
            FoodDataModel(
                getString(R.string.bulgur_name),
                R.drawable.bulgur,
                getString(R.string.bulgur_description)
            ),
            FoodDataModel(
                getString(R.string.farro_name),
                R.drawable.farro,
                getString(R.string.farro_description)
            ),
            FoodDataModel(
                getString(R.string.millet_name),
                R.drawable.millet,
                getString(R.string.millet_description)
            ),
            FoodDataModel(
                getString(R.string.buckwheat_name),
                R.drawable.buckwheat,
                getString(R.string.buckwheat_description)
            )
        )

        // vegetables
        val vegetablesList = mutableListOf(
            FoodDataModel(
                getString(R.string.broccoli_name),
                R.drawable.broccoli,
                getString(R.string.broccoli_description)
            ),
            FoodDataModel(
                getString(R.string.spinach_name),
                R.drawable.spinach,
                getString(R.string.spinach_description)
            ),
            FoodDataModel(
                getString(R.string.carrots_name),
                R.drawable.carrots,
                getString(R.string.carrots_description)
            ),
            FoodDataModel(
                getString(R.string.bell_peppers_name),
                R.drawable.bell_peppers,
                getString(R.string.bell_peppers_description)
            ),
            FoodDataModel(
                getString(R.string.cauliflower_name),
                R.drawable.cauliflower,
                getString(R.string.cauliflower_description)
            ),
            FoodDataModel(
                getString(R.string.zucchini_name),
                R.drawable.zucchini,
                getString(R.string.zucchini_description)
            ),
            FoodDataModel(
                getString(R.string.sweet_potato_name),
                R.drawable.sweet_potato,
                getString(R.string.sweet_potato_description)
            ),
            FoodDataModel(
                getString(R.string.kale_name),
                R.drawable.kale,
                getString(R.string.kale_description)
            ),
            FoodDataModel(
                getString(R.string.asparagus_name),
                R.drawable.asparagus,
                getString(R.string.asparagus_description)
            ),
            FoodDataModel(
                getString(R.string.tomato_name),
                R.drawable.tomato,
                getString(R.string.tomato_description)
            )
        )

        // dairy
        val dairyList = mutableListOf(
            FoodDataModel(
                getString(R.string.greek_yogurt_name),
                R.drawable.greek_yogurt,
                getString(R.string.greek_yogurt_description)
            ),
            FoodDataModel(
                getString(R.string.milk_name),
                R.drawable.milk,
                getString(R.string.milk_description)
            ),
            FoodDataModel(
                getString(R.string.cheese_name),
                R.drawable.cheese,
                getString(R.string.cheese_description)
            ),
            FoodDataModel(
                getString(R.string.cottage_cheese_name),
                R.drawable.cottage_cheese,
                getString(R.string.cottage_cheese_description)
            ),
            FoodDataModel(
                getString(R.string.butter_name),
                R.drawable.butter,
                getString(R.string.butter_description)
            ),
            FoodDataModel(
                getString(R.string.yogurt_name),
                R.drawable.yogurt,
                getString(R.string.yogurt_description)
            ),
            FoodDataModel(
                getString(R.string.soy_milk_name),
                R.drawable.soy_milk,
                getString(R.string.soy_milk_description)
            ),
            FoodDataModel(
                getString(R.string.almond_milk_name),
                R.drawable.almond_milk,
                getString(R.string.almond_milk_description)
            ),
            FoodDataModel(
                getString(R.string.goat_cheese_name),
                R.drawable.goat_cheese,
                getString(R.string.goat_cheese_description)
            ),
            FoodDataModel(
                getString(R.string.cream_name),
                R.drawable.cream,
                getString(R.string.cream_description)
            )
        )

        // nuts and seeds
        val nutsAndSeedsList = mutableListOf(
            FoodDataModel(
                getString(R.string.almonds_name),
                R.drawable.almonds,
                getString(R.string.almonds_description)
            ),
            FoodDataModel(
                getString(R.string.chia_seeds_name),
                R.drawable.chia_seeds,
                getString(R.string.chia_seeds_description)
            ),
            FoodDataModel(
                getString(R.string.walnuts_name),
                R.drawable.walnuts,
                getString(R.string.walnuts_description)
            ),
            FoodDataModel(
                getString(R.string.flaxseeds_name),
                R.drawable.flaxseeds,
                getString(R.string.flaxseeds_description)
            ),
            FoodDataModel(
                getString(R.string.sunflower_seeds_name),
                R.drawable.sunflower_seeds,
                getString(R.string.sunflower_seeds_description)
            ),
            FoodDataModel(
                getString(R.string.pumpkin_seeds_name),
                R.drawable.pumpkin_seeds,
                getString(R.string.pumpkin_seeds_description)
            ),
            FoodDataModel(
                getString(R.string.cashews_name),
                R.drawable.cashews,
                getString(R.string.cashews_description)
            ),
            FoodDataModel(
                getString(R.string.peanuts_name),
                R.drawable.peanuts,
                getString(R.string.peanuts_description)
            ),
            FoodDataModel(
                getString(R.string.hazelnuts_name),
                R.drawable.hazelnuts,
                getString(R.string.hazelnuts_description)
            ),
            FoodDataModel(
                getString(R.string.pistachios_name),
                R.drawable.pistachios,
                getString(R.string.pistachios_description)
            )
        )

        //dropdown selection
        binding.foodCategoryDropDown.setOnItemClickListener { _, _, position, _ ->
            var selectedList = when (position) {
                0 -> fruitsList
                1 -> proteinsList
                2 -> grainsList
                3 -> vegetablesList
                4 -> dairyList
                5 -> nutsAndSeedsList
                else -> {
                    mutableListOf<FoodDataModel>()
                }
            }
            adapter.updateData(selectedList);
        }
    }

    private fun changeTheme(themeColor: String?): Int {
        val theme = super.getTheme()
        when (themeColor) {
            "Red" -> {
                theme.applyStyle(R.style.Base_Theme_Red, true)
                return R.color.primary
            }

            "Orange" -> {
                theme.applyStyle(R.style.Base_Theme_Orange, true)
                return R.color.orange
            }

            "Brown" -> {
                theme.applyStyle(R.style.Base_Theme_Brown, true)
                return R.color.brown
            }
        }
        return 0
    }
}