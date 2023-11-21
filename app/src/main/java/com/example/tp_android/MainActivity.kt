package com.example.tp_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.tp_android.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    var TAUX = 1.0
    val tauxD_E = 1.09
    val tauxE_Y = 163.41
    val tauxD_Y = 150.34
    var bas :Double=0.0
    var haut :Double=0.0
    val PATHERN = "[-+]?\\d+(\\.\\d+)?"
    val DF = DecimalFormat("#.00")



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.buttonBas.setOnClickListener{
            if (Regex(PATHERN).matches(binding.editTextHaut.text.toString().replace(',','.'))) {
                haut = binding.editTextHaut.text.toString().replace(',', '.').toDouble()
                bas = haut * TAUX
                binding.editTextBas.setText(DF.format(bas))
            }
            else{
                Toast.makeText(this,"erreur de saisie", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonHaut.setOnClickListener{
            if (Regex(PATHERN).matches(binding.editTextBas.text.toString().replace(',','.'))) {
                bas = binding.editTextBas.text.toString().replace(',', '.').toDouble()
                haut = bas/TAUX
                binding.editTextHaut.setText(DF.format(haut))
            }
            else{
                Toast.makeText(this,"erreur de saisie", Toast.LENGTH_SHORT).show()
            }
        }
        binding.rbDollardYen.setOnClickListener{
            TAUX = tauxD_Y
            binding.tvHaut.setText(R.string.dollard)
            binding.tvBas.setText(R.string.yen)
        }
        binding.rbEuroYen.setOnClickListener{
            TAUX = tauxE_Y
            binding.tvHaut.setText(R.string.euro)
            binding.tvBas.setText(R.string.yen)
        }
        binding.rbEuroDollard.setOnClickListener{
            TAUX = tauxD_E
            binding.tvHaut.setText(R.string.euro)
            binding.tvBas.setText(R.string.dollard)
        }
    }
}