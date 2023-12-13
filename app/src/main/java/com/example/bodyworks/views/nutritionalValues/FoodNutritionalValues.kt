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
        val foodCategories =
            arrayOf("Fruits", "Protein Foods", "Grains", "Vegetables", "Dairy", "Nuts and Seeds")
        val foodCategoriesAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, foodCategories)
        binding.foodCategoryDropDown.setAdapter(foodCategoriesAdapter)

        //recycler view
        binding.foodRecyclerView.setHasFixedSize(true)
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this)

        // creating lists
        val fruitsList = mutableListOf(
            FoodDataModel(
                "Apple",
                R.drawable.apple,
                "• Calories: 52 kcal\n• Carbohydrates: 14 g\n• Fiber: 2.4 g\n• Sugars: 10 g\n• Protein: 0.3 g"
            ),
            FoodDataModel(
                "Banana",
                R.drawable.banana,
                "• Calories: 89 kcal\n• Carbohydrates: 23 g\n• Fiber: 2.6 g\n• Sugars: 12 g\n• Protein: 1.1 g"
            ),
            FoodDataModel(
                "Orange",
                R.drawable.orange,
                "• Calories: 43 kcal\n• Carbohydrates: 9 g\n• Fiber: 2.3 g\n• Sugars: 8.2 g\n• Protein: 1.0 g"
            ),
            FoodDataModel(
                "Strawberries",
                R.drawable.strawberries,
                "• Calories: 32 kcal\n• Carbohydrates: 7.7 g\n• Fiber: 2.0 g\n• Sugars: 4.9 g\n• Protein: 0.7 g"
            ),
            FoodDataModel(
                "Pineapple",
                R.drawable.pineapple,
                "• Calories: 50 kcal\n• Carbohydrates: 13 g\n• Fiber: 1.4 g\n• Sugars: 9.9 g\n• Protein: 0.5 g"
            ),
            FoodDataModel(
                "Grapes",
                R.drawable.grapes,
                "• Calories: 69 kcal\n• Carbohydrates: 18 g\n• Fiber: 0.9 g\n• Sugars: 15 g\n• Protein: 0.6 g"
            ),
            FoodDataModel(
                "Mango",
                R.drawable.mango,
                "• Calories: 60 kcal\n• Carbohydrates: 15 g\n• Fiber: 1.6 g\n• Sugars: 14 g\n• Protein: 0.8 g"
            ),
            FoodDataModel(
                "Watermelon",
                R.drawable.watermelon,
                "• Calories: 30 kcal\n• Carbohydrates: 8 g\n• Fiber: 0.4 g\n• Sugars: 6 g\n• Protein: 0.6 g"
            ),
            FoodDataModel(
                "Peach",
                R.drawable.peach,
                "• Calories: 39 kcal\n• Carbohydrates: 10 g\n• Fiber: 1.5 g\n• Sugars: 8.4 g\n• Protein: 0.9 g"
            ),
            FoodDataModel(
                "Kiwi",
                R.drawable.kiwi,
                "• Calories: 61 kcal\n• Carbohydrates: 15 g\n• Fiber: 3 g\n• Sugars: 9 g\n• Protein: 1.1 g"
            )
        )

        // Proteins
        val proteinsList = mutableListOf(
            FoodDataModel(
                "Chicken Breast",
                R.drawable.chicken_breast,
                "• Calories: 165 kcal\n• Protein: 31 g\n• Total Fat: 3.6 g\n• Saturated Fat: 1.0 g\n• Cholesterol: 85 mg"
            ),
            FoodDataModel(
                "Salmon",
                R.drawable.salmon,
                "• Calories: 206 kcal\n• Protein: 22 g\n• Total Fat: 13 g\n• Saturated Fat: 2.0 g\n• Omega-3 Fatty Acids: 2.3 g"
            ),
            FoodDataModel(
                "Egg",
                R.drawable.egg,
                "• Calories: 68 kcal\n• Protein: 5.5 g\n• Total Fat: 4.8 g\n• Saturated Fat: 1.6 g\n• Cholesterol: 186 mg"
            ),
            FoodDataModel(
                "Tofu",
                R.drawable.tofu,
                "• Calories: 144 kcal\n• Protein: 15 g\n• Total Fat: 8.0 g\n• Saturated Fat: 1.1 g\n• Cholesterol: 0 mg"
            ),
            FoodDataModel(
                "Turkey",
                R.drawable.turkey,
                "• Calories: 135 kcal\n• Protein: 29 g\n• Total Fat: 1.7 g\n• Saturated Fat: 0.5 g\n• Cholesterol: 70 mg"
            ),
            FoodDataModel(
                "Beef",
                R.drawable.beef,
                "• Calories: 250 kcal\n• Protein: 26 g\n• Total Fat: 17 g\n• Saturated Fat: 6.8 g\n• Cholesterol: 94 mg"
            ),
            FoodDataModel(
                "Pork",
                R.drawable.pork,
                "• Calories: 143 kcal\n• Protein: 25 g\n• Total Fat: 4.4 g\n• Saturated Fat: 1.5 g\n• Cholesterol: 71 mg"
            ),
            FoodDataModel(
                "Lentils",
                R.drawable.lentils,
                "• Calories: 116 kcal\n• Protein: 9.0 g\n• Total Fat: 0.4 g\n• Saturated Fat: 0.1 g\n• Cholesterol: 0 mg"
            ),
            FoodDataModel(
                "Chickpeas",
                R.drawable.chickpeas,
                "• Calories: 164 kcal\n• Protein: 8.9 g\n• Total Fat: 2.6 g\n• Saturated Fat: 0.3 g\n• Cholesterol: 0 mg"
            ),
            FoodDataModel(
                "Quinoa",
                R.drawable.quinoa,
                "• Calories: 120 kcal\n• Protein: 4.1 g\n• Total Fat: 1.9 g\n• Saturated Fat: 0.2 g\n• Cholesterol: 0 mg"
            )
        )

        // Grains
        val grainsList = mutableListOf(
            FoodDataModel(
                "Brown Rice",
                R.drawable.brown_rice,
                "• Calories: 111 kcal\n• Carbohydrates: 23 g\n• Fiber: 1.8 g\n• Protein: 2.6 g"
            ),
            FoodDataModel(
                "Quinoa",
                R.drawable.quinoa,
                "• Calories: 120 kcal\n• Carbohydrates: 21 g\n• Fiber: 2.8 g\n• Protein: 4.1 g"
            ),
            FoodDataModel(
                "Barley",
                R.drawable.barley,
                "• Calories: 123 kcal\n• Carbohydrates: 28 g\n• Fiber: 4.0 g\n• Protein: 3.6 g"
            ),
            FoodDataModel(
                "Oats",
                R.drawable.oats,
                "• Calories: 68 kcal\n• Carbohydrates: 12 g\n• Fiber: 2.4 g\n• Protein: 2.4 g"
            ),
            FoodDataModel(
                "Whole Wheat Bread",
                R.drawable.whole_wheat_bread,
                "• Calories: 247 kcal\n• Carbohydrates: 49 g\n• Fiber: 7.6 g\n• Protein: 12 g"
            ),
            FoodDataModel(
                "Couscous",
                R.drawable.couscous,
                "• Calories: 176 kcal\n• Carbohydrates: 36 g\n• Fiber: 2.2 g\n• Protein: 6 g"
            ),
            FoodDataModel(
                "Bulgur",
                R.drawable.bulgur,
                "• Calories: 83 kcal\n• Carbohydrates: 18 g\n• Fiber: 5.6 g\n• Protein: 3 g"
            ),
            FoodDataModel(
                "Farro",
                R.drawable.farro,
                "• Calories: 170 kcal\n• Carbohydrates: 34 g\n• Fiber: 5.4 g\n• Protein: 7 g"
            ),
            FoodDataModel(
                "Millet",
                R.drawable.millet,
                "• Calories: 119 kcal\n• Carbohydrates: 23 g\n• Fiber: 1.7 g\n• Protein: 3.5 g"
            ),
            FoodDataModel(
                "Buckwheat",
                R.drawable.buckwheat,
                "• Calories: 343 kcal\n• Carbohydrates: 71 g\n• Fiber: 10 g\n• Protein: 13 g"
            )
        )

        // vegetables
        val vegetablesList = mutableListOf(
            FoodDataModel(
                "Broccoli",
                R.drawable.broccoli,
                "• Calories: 55 kcal\n• Carbohydrates: 11 g\n• Fiber: 3.7 g\n• Protein: 3.7 g"
            ),
            FoodDataModel(
                "Spinach",
                R.drawable.spinach,
                "• Calories: 23 kcal\n• Carbohydrates: 3.6 g\n• Fiber: 2.2 g\n• Protein: 2.9 g"
            ),
            FoodDataModel(
                "Carrots",
                R.drawable.carrots,
                "• Calories: 41 kcal\n• Carbohydrates: 10 g\n• Fiber: 2.8 g\n• Protein: 1 g"
            ),
            FoodDataModel(
                "Bell Peppers",
                R.drawable.bell_peppers,
                "• Calories: 31 kcal\n• Carbohydrates: 7.6 g\n• Fiber: 2.8 g\n• Protein: 1.2 g"
            ),
            FoodDataModel(
                "Cauliflower",
                R.drawable.cauliflower,
                "• Calories: 25 kcal\n• Carbohydrates: 5.3 g\n• Fiber: 2.5 g\n• Protein: 2 g"
            ),
            FoodDataModel(
                "Zucchini",
                R.drawable.zucchini,
                "• Calories: 17 kcal\n• Carbohydrates: 3.1 g\n• Fiber: 1 g\n• Protein: 1.2 g"
            ),
            FoodDataModel(
                "Sweet Potato",
                R.drawable.sweet_potato,
                "• Calories: 86 kcal\n• Carbohydrates: 20 g\n• Fiber: 3 g\n• Protein: 2 g"
            ),
            FoodDataModel(
                "Kale",
                R.drawable.kale,
                "• Calories: 50 kcal\n• Carbohydrates: 11 g\n• Fiber: 2.6 g\n• Protein: 3.3 g"
            ),
            FoodDataModel(
                "Asparagus",
                R.drawable.asparagus,
                "• Calories: 20 kcal\n• Carbohydrates: 3.7 g\n• Fiber: 2 g\n• Protein: 2.2 g"
            ),
            FoodDataModel(
                "Tomato",
                R.drawable.tomato,
                "• Calories: 18 kcal\n• Carbohydrates: 4 g\n• Fiber: 1.5 g\n• Protein: 1 g"
            )
        )

        // dairy
        val dairyList = mutableListOf(
            FoodDataModel(
                "Greek Yogurt",
                R.drawable.greek_yogurt,
                "• Calories: 59 kcal\n• Protein: 10 g\n• Carbohydrates: 3.6 g\n• Sugars: 3.6 g"
            ),
            FoodDataModel(
                "Milk",
                R.drawable.milk,
                "• Calories: 61 kcal\n• Protein: 3.2 g\n• Carbohydrates: 4.8 g\n• Sugars: 4.8 g"
            ),
            FoodDataModel(
                "Cheese",
                R.drawable.cheese,
                "• Calories: 402 kcal\n• Protein: 25 g\n• Total Fat: 33 g\n• Saturated Fat: 21 g\n• Cholesterol: 100 mg"
            ),
            FoodDataModel(
                "Cottage Cheese",
                R.drawable.cottage_cheese,
                "• Calories: 98 kcal\n• Protein: 11 g\n• Carbohydrates: 3.4 g\n• Sugars: 2.3 g"
            ),
            FoodDataModel(
                "Butter",
                R.drawable.butter,
                "• Calories: 717 kcal\n• Total Fat: 81 g\n• Saturated Fat: 51 g\n• Cholesterol: 215 mg"
            ),
            FoodDataModel(
                "Yogurt",
                R.drawable.yogurt,
                "• Calories: 59 kcal\n• Protein: 10 g\n• Carbohydrates: 3.6 g\n• Sugars: 3.6 g"
            ),
            FoodDataModel(
                "Soy Milk",
                R.drawable.soy_milk,
                "• Calories: 33 kcal\n• Protein: 3.3 g\n• Carbohydrates: 1.7 g\n• Sugars: 1 g"
            ),
            FoodDataModel(
                "Almond Milk",
                R.drawable.almond_milk,
                "• Calories: 13 kcal\n• Protein: 0.5 g\n• Carbohydrates: 0.6 g\n• Sugars: 0.1 g"
            ),
            FoodDataModel(
                "Goat Cheese",
                R.drawable.goat_cheese,
                "• Calories: 364 kcal\n• Protein: 22 g\n• Total Fat: 30 g\n• Saturated Fat: 20 g\n• Cholesterol: 59 mg"
            ),
            FoodDataModel(
                "Cream",
                R.drawable.cream,
                "• Calories: 340 kcal\n• Protein: 2 g\n• Total Fat: 36 g\n• Saturated Fat: 22 g\n• Cholesterol: 110 mg"
            )
        )

        // nuts and seeds
        val nutsAndSeedsList = mutableListOf(
            FoodDataModel(
                "Almonds",
                R.drawable.almonds,
                "• Calories: 576 kcal\n• Protein: 21 g\n• Total Fat: 49 g\n• Saturated Fat: 3.7 g\n• Fiber: 12 g"
            ),
            FoodDataModel(
                "Chia Seeds",
                R.drawable.chia_seeds,
                "• Calories: 486 kcal\n• Protein: 16.5 g\n• Total Fat: 30.7 g\n• Saturated Fat: 3.3 g\n• Fiber: 34.4 g"
            ),
            FoodDataModel(
                "Walnuts",
                R.drawable.walnuts,
                "• Calories: 654 kcal\n• Protein: 15 g\n• Total Fat: 65 g\n• Saturated Fat: 6.1 g\n• Fiber: 7 g"
            ),
            FoodDataModel(
                "Flaxseeds",
                R.drawable.flaxseeds,
                "• Calories: 534 kcal\n• Protein: 18 g\n• Total Fat: 42 g\n• Saturated Fat: 4 g\n• Fiber: 27 g"
            ),
            FoodDataModel(
                "Sunflower Seeds",
                R.drawable.sunflower_seeds,
                "• Calories: 584 kcal\n• Protein: 21 g\n• Total Fat: 51 g\n• Saturated Fat: 4.5 g\n• Fiber: 8.6 g"
            ),
            FoodDataModel(
                "Pumpkin Seeds",
                R.drawable.pumpkin_seeds,
                "• Calories: 574 kcal\n• Protein: 30 g\n• Total Fat: 49 g\n• Saturated Fat: 8.7 g\n• Fiber: 5.2 g"
            ),
            FoodDataModel(
                "Cashews",
                R.drawable.cashews,
                "• Calories: 553 kcal\n• Protein: 18 g\n• Total Fat: 44 g\n• Saturated Fat: 8.5 g\n• Fiber: 3.3 g"
            ),
            FoodDataModel(
                "Peanuts",
                R.drawable.peanuts,
                "• Calories: 567 kcal\n• Protein: 25 g\n• Total Fat: 49 g\n• Saturated Fat: 6.9 g\n• Fiber: 8.5 g"
            ),
            FoodDataModel(
                "Hazelnuts",
                R.drawable.hazelnuts,
                "• Calories: 628 kcal\n• Protein: 15 g\n• Total Fat: 61 g\n• Saturated Fat: 4.5 g\n• Fiber: 9.7 g"
            ),
            FoodDataModel(
                "Pistachios",
                R.drawable.pistachios,
                "• Calories: 562 kcal\n• Protein: 21 g\n• Total Fat: 45 g\n• Saturated Fat: 5.4 g\n• Fiber: 10 g"
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