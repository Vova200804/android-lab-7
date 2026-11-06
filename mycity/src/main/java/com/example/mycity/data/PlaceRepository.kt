package com.example.mycity.data
import com.example.mycity.R
data class Place(val id:String,val category:Int,val image:Int,val title:Int,val address:Int,val description:Int,val source:Int)
object PlaceRepository {
 val categories=listOf("Рестораны","Парки","Кофейни","Кинотеатры","Торговые центры")
 val places=listOf(
 Place("port42",0,R.drawable.port42,R.string.port42_title,R.string.port42_address,R.string.port42_description,R.string.port42_source),
 Place("zaboy",0,R.drawable.zaboy,R.string.zaboy_title,R.string.zaboy_address,R.string.zaboy_description,R.string.zaboy_source),
 Place("volna",0,R.drawable.volna,R.string.volna_title,R.string.volna_address,R.string.volna_description,R.string.volna_source),
 Place("park_wonders",1,R.drawable.park_wonders,R.string.park_wonders_title,R.string.park_wonders_address,R.string.park_wonders_description,R.string.park_wonders_source),
 Place("park_antoshka",1,R.drawable.park_antoshka,R.string.park_antoshka_title,R.string.park_antoshka_address,R.string.park_antoshka_description,R.string.park_antoshka_source),
 Place("park_birch",1,R.drawable.park_birch,R.string.park_birch_title,R.string.park_birch_address,R.string.park_birch_description,R.string.park_birch_source),
 Place("portcoffee",2,R.drawable.portcoffee,R.string.portcoffee_title,R.string.portcoffee_address,R.string.portcoffee_description,R.string.portcoffee_source),
 Place("kofebulka",2,R.drawable.kofebulka,R.string.kofebulka_title,R.string.kofebulka_address,R.string.kofebulka_description,R.string.kofebulka_source),
 Place("coffeeman",2,R.drawable.coffeeman,R.string.coffeeman_title,R.string.coffeeman_address,R.string.coffeeman_description,R.string.coffeeman_source),
 Place("kosmos",3,R.drawable.kosmos,R.string.kosmos_title,R.string.kosmos_address,R.string.kosmos_description,R.string.kosmos_source),
 Place("starmax",3,R.drawable.starmax,R.string.starmax_title,R.string.starmax_address,R.string.starmax_description,R.string.starmax_source),
 Place("planetakino",3,R.drawable.planetakino,R.string.planetakino_title,R.string.planetakino_address,R.string.planetakino_description,R.string.planetakino_source),
 Place("promenad2",4,R.drawable.promenad2,R.string.promenad2_title,R.string.promenad2_address,R.string.promenad2_description,R.string.promenad2_source),
 Place("promenad3",4,R.drawable.promenad3,R.string.promenad3_title,R.string.promenad3_address,R.string.promenad3_description,R.string.promenad3_source),
 Place("letocity",4,R.drawable.letocity,R.string.letocity_title,R.string.letocity_address,R.string.letocity_description,R.string.letocity_source))
 fun inCategory(category:Int)=places.filter{it.category==category}
}
