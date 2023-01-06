package com.kibzation.cvsnbatakehome.view

import android.content.Context
import android.widget.ArrayAdapter
import com.kibzation.cvsnbatakehome.model.Team

class TeamAdapter(var teams:List<Team>?, context: Context, resource: Int):
    ArrayAdapter<Team>(context, resource) {

    override fun getCount(): Int {
        return teams?.count() ?: 0
    }

    override fun getItem(position: Int): Team? {
        return teams?.get(position)
    }
}