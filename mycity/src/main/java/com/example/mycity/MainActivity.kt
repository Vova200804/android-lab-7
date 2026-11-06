@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
package com.example.mycity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.mycity.data.*
import com.example.mycity.ui.*

class MainActivity:ComponentActivity(){override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);enableEdgeToEdge();setContent{MaterialTheme(colorScheme=lightColorScheme(primary=Color(0xFF245E69),secondary=Color(0xFF7B5738),surface=Color(0xFFFAF9F5),background=Color(0xFFFAF9F5))){CityApp()}}}}
@Composable fun CityApp(vm:CityViewModel=viewModel(),nav:NavHostController=rememberNavController()){
 val state by vm.uiState.collectAsStateWithLifecycle()
 val entry by nav.currentBackStackEntryAsState()
 val details=entry?.destination?.route=="place/{id}"
 val selected=PlaceRepository.places.first{it.id==state.selectedId}
 BoxWithConstraints(Modifier.fillMaxSize()){
 val expanded=maxWidth>=840.dp
 val medium=maxWidth>=600.dp
 val chooseCategory:(Int)->Unit={cat->vm.selectCategory(cat);nav.navigate("places/$cat"){popUpTo("places/0"){inclusive=false};launchSingleTop=true}}
 val choosePlace:(Place)->Unit={place->vm.selectPlace(place.id);nav.navigate("place/${place.id}"){launchSingleTop=true}}
 Scaffold(topBar={TopAppBar(title={Column{Text("Мой Кемерово");Text(PlaceRepository.categories[state.category],style=MaterialTheme.typography.labelLarge)}},navigationIcon={if(details&&!expanded)TextButton({nav.popBackStack()}){Text("Назад")}})},bottomBar={if(!medium)NavigationBar{listOf("Еда","Парки","Кофе","Кино","Покупки").forEachIndexed{index,title->NavigationBarItem(selected=state.category==index,onClick={chooseCategory(index)},icon={CategoryIcon(index)},label={Text(title)},modifier=Modifier.testTag("category_$index"))}}}){padding->
 Row(Modifier.padding(padding).fillMaxSize()){
 if(expanded)PermanentDrawerSheet(Modifier.width(220.dp)){Spacer(Modifier.height(20.dp));Text("Откройте город",Modifier.padding(20.dp),style=MaterialTheme.typography.titleLarge);PlaceRepository.categories.forEachIndexed{index,title->NavigationDrawerItem(label={Text(title)},selected=state.category==index,onClick={chooseCategory(index)},modifier=Modifier.padding(horizontal=12.dp,vertical=3.dp).testTag("category_$index"))}}
 else if(medium)NavigationRail{PlaceRepository.categories.forEachIndexed{index,title->NavigationRailItem(selected=state.category==index,onClick={chooseCategory(index)},icon={CategoryIcon(index)},label={Text(title)},modifier=Modifier.testTag("category_$index"))}}
 NavHost(nav,startDestination="places/0",modifier=Modifier.weight(1f)){
 composable("places/{category}"){if(expanded)Row{PlaceList(state,choosePlace,Modifier.weight(0.9f));PlaceDetails(selected,Modifier.weight(1.1f))}else PlaceList(state,choosePlace,Modifier.fillMaxSize())}
 composable("place/{id}"){if(expanded)Row{PlaceList(state,choosePlace,Modifier.weight(0.9f));PlaceDetails(selected,Modifier.weight(1.1f))}else PlaceDetails(selected,Modifier.fillMaxSize())}
 }
 }
 }
 }
}
@Composable fun CategoryIcon(index:Int){Text(listOf("Р","П","К","Ф","Т")[index],style=MaterialTheme.typography.titleMedium)}
@Composable fun PlaceList(state:CityUiState,onClick:(Place)->Unit,modifier:Modifier){LazyColumn(modifier.padding(horizontal=16.dp),verticalArrangement=Arrangement.spacedBy(14.dp),contentPadding=PaddingValues(vertical=16.dp)){
 item{Text("Места для вашего дня",style=MaterialTheme.typography.headlineSmall);Text("3 места · Кемерово",style=MaterialTheme.typography.bodyMedium,modifier=Modifier.padding(top=6.dp))}
 items(PlaceRepository.inCategory(state.category),key={it.id}){place->Card(onClick={onClick(place)},modifier=Modifier.fillMaxWidth().testTag("place_${place.id}")){
 Image(painterResource(place.image),stringResource(place.title),Modifier.fillMaxWidth().height(148.dp),contentScale=ContentScale.Crop)
 Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(6.dp)){Text(stringResource(place.title),style=MaterialTheme.typography.titleLarge);Text(stringResource(place.address),style=MaterialTheme.typography.bodyMedium)}
 }}
}}
@Composable fun PlaceDetails(place:Place,modifier:Modifier){val context=LocalContext.current;val url=stringResource(place.source)
 Column(modifier.padding(20.dp).verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(18.dp)){
 Image(painterResource(place.image),stringResource(place.title),Modifier.fillMaxWidth().height(260.dp),contentScale=ContentScale.Crop)
 Text(stringResource(place.title),style=MaterialTheme.typography.headlineMedium,modifier=Modifier.testTag("detail_title"))
 Text(stringResource(place.address),style=MaterialTheme.typography.titleMedium)
 Text(stringResource(place.description),style=MaterialTheme.typography.bodyLarge)
 OutlinedButton(onClick={context.startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(url)))}){Text("Подробнее о месте")}
 }
}
