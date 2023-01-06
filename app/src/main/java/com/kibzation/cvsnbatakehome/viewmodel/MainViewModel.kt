package com.kibzation.cvsnbatakehome.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kibzation.cvsnbatakehome.model.Team
import com.kibzation.cvsnbatakehome.model.TeamsService
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    var team=MutableLiveData<Team>()
    var teamJob:Job?=null
    val teamsService=TeamsService.getTeamService()
    val teams = MutableLiveData<List<Team>>()
    val loading = MutableLiveData<Boolean>()
    val teamLoadError=MutableLiveData<String?>()
    val exceptionHandler= CoroutineExceptionHandler { _, throwable -> onTeamLoadError("Exception ${throwable.message}") }


    fun fetchCountries(){
        loading.value=true
       teamJob= CoroutineScope(Dispatchers.IO + exceptionHandler)
            .launch {
                val response=teamsService.getTeams()
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        teams.value=response.body()?.teams
                        loading.value=false
                    }else{
                        onTeamLoadError("Error ${response.errorBody()}")
                    }
                }

        }
    }
fun setupTeam(team: Team){
    this.team.value=team
}
private fun onTeamLoadError(message:String){
    teamLoadError.value=message
    loading.value=false
}

    override fun onCleared() {
        super.onCleared()
        teamJob?.cancel()
    }
}