package com.amanda.poscovid.ui.fragment

import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.amanda.poscovid.R
import com.amanda.poscovid.ui.activity.escondeTeclado

abstract class BaseAppFragment : Fragment() {

    protected val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    fun configuraSenhaEditText(editText: EditText, imageView: ImageView) {
        escondeSenha(imageView, editText)

        imageView.setOnClickListener {
            activity?.escondeTeclado()
            editText.clearFocus()
            if (editText.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                escondeSenha(imageView, editText)
            } else {
                mostraSenha(imageView, editText)
            }
        }
    }

    private fun mostraSenha(imageView: ImageView, editText: EditText) {
        imageView.setImageResource(R.drawable.ic_olho)
        editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }

    private fun escondeSenha(imageView: ImageView, editText: EditText) {
        imageView.setImageResource(R.drawable.ic_olho_cortado)
        editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun mostrarAlerta(mensagem: String, listener: (() -> Unit)? = null) {
        context?.let {
            AlertDialog.Builder(it)
                .setCancelable(false)
                .setMessage(mensagem)
                .setPositiveButton("ok") { _, _ ->
                    listener?.invoke()
                }
                .create().show()
        }
    }

    fun navigateTo(navDirections: NavDirections) {
        try {
            navController.navigate(navDirections)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}