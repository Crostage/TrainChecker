package com.crostage.trainchecker.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.helper.Constant

class RoutesFragment: Fragment(R.layout.fragment_routes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainNum = view.findViewById<TextView>(R.id.trainNumber)
        val trainFromTo = view.findViewById<TextView>(R.id.fromTo)
        val date = view.findViewById<TextView>(R.id.date)

        val train = arguments?.getSerializable(Constant.TRAIN_ROUTS) as Train

        train?.let {
            trainNum.text = train.number
            trainFromTo.text = "${train.route0} -> ${train.route1}"
            date.text = train.date0
        }
    }
}