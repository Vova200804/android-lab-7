package com.example.mycity
import org.junit.Test
import org.junit.Assert.*
import com.example.mycity.data.PlaceRepository
import com.example.mycity.ui.CityViewModel
class CityViewModelTest{
 @Test fun each_category_has_three_unique_places(){assertEquals(15,PlaceRepository.places.map{it.id}.toSet().size);repeat(5){assertEquals(3,PlaceRepository.inCategory(it).size)}}
 @Test fun category_and_place_selection(){val vm=CityViewModel();vm.selectCategory(1);assertEquals(1,vm.uiState.value.category);vm.selectPlace("park_birch");assertEquals("park_birch",vm.uiState.value.selectedId)}
}
