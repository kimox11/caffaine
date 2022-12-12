package com.example.caffaine

class DataManager {
    companion object{
        var list_of_restaurants:ArrayList<restaurant> = ArrayList()

        fun setData(){
            //set Resaurants menu
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "BOLO",
                    "Hsdfsdfsdf\n" +
                            "gfhgfjgf\n" +
                            "Ho ho ho ho ho ho ho"
                )
            )
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "rtyy",
                    "Ho hsdfdsfsdfho ho ho ho ho ho"

                ))
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "uuuu",
                    "Ho ho ho ho ho ho ho"
                ))
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "hardes",
                    "chesseeeee"

                ))
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "heart attack",
                    "attaccckkkk"
                ))
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "M",
                    "Ho ho ho Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "KK",
                    "o ho ho ho\n" +
                            "Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "7amza",
                    "Hsdfsdfsdfo ho\n" +
                            "Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "KFS",
                    "o ho ho ho ho Ho ho ho ho ho ho ho\n" +
                            "fsdfsdfsdf\n" +
                            " ho ho ho"
                ))
            list_of_restaurants.add(
                restaurant(
                    R.drawable.mac,
                    "Mac",
                    "Ho ho ho ho ho ho ho Ho ho ho ho ho ho ho Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))


            // add meal data for each restaurant
            var menu:ArrayList<restaurant> = ArrayList()
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 1",
                    "Hsdfsdfsdf\n" +
                            "gfhgfjgf\n" +
                            "Ho ho ho ho ho ho ho"
                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 2",
                    "Ho hsdfdsfsdfho ho ho ho ho ho"

                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 3",
                    "Ho ho ho ho ho ho ho"
                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 4",
                    "chesseeeee"

                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 5",
                    "attaccckkkk"
                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 6",
                    "Ho ho ho Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 7",
                    "o ho ho ho\n" +
                            "Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 8",
                    "Hsdfsdfsdfo ho\n" +
                            "Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 9",
                    "o ho ho ho ho Ho ho ho ho ho ho ho\n" +
                            "fsdfsdfsdf\n" +
                            " ho ho ho"
                ))
            menu.add(
                restaurant(
                    R.drawable.mac,
                    "Meal 10",
                    "Ho ho ho ho ho ho ho Ho ho ho ho ho ho ho Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))

            for (restaurant in list_of_restaurants){
                restaurant.meals = menu
            }
        }
    }
    

}