package com.ngrb.tuweqd5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ngrb.tuweqd5.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        binding.calculateButton.setOnClickListener{
            calculatVat()
        }

    }

    private fun calculatVat(){
    val stringcost=binding.costOfService.text.toString()
    val cost=stringcost.toDoubleOrNull()
        if (cost==null){
            binding.tipResult.text=""
            Toast.makeText(applicationContext, "Please enter a Number! ", Toast.LENGTH_SHORT).show()

            return
        }

        val selectedid=binding.tipOptions.checkedRadioButtonId

        val vatprecentage=when(selectedid){
            binding.optionTwentyPercent.id->0.1
            binding.optionEighteenPercent.id->0.15
            else->0.2
        }

        val vat=vatprecentage*cost
        var total=vat+cost
        val switchstate=binding.roundUpSwitch.isChecked
        if(switchstate){
            total=kotlin.math.ceil(total)
        }

        val formattedtotal=NumberFormat.getCurrencyInstance().format(total)

        binding.tipResult.text=getString(R.string.total_amount,formattedtotal)
    }
}