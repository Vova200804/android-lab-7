package com.example.mycity.ui
import androidx.lifecycle.ViewModel
import com.example.mycity.data.PlaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
data class CityUiState(val category:Int=0,val selectedId:String=PlaceRepository.places.first().id)
class CityViewModel:ViewModel(){
 private val _uiState=MutableStateFlow(CityUiState())
 val uiState=_uiState.asStateFlow()
 fun selectCategory(category:Int){_uiState.value=CityUiState(category,PlaceRepository.inCategory(category).first().id)}
 fun selectPlace(id:String){_uiState.update{it.copy(selectedId=id)}}
}
