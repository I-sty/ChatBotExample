package com.example.chatbot.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.chatbot.R
import org.koin.android.viewmodel.ext.android.getViewModel
import timber.log.Timber
import kotlin.reflect.KClass

abstract class BaseActivity<V : ViewDataBinding, M : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: V
    protected lateinit var viewModel: M

    abstract fun layoutRes(): Int

    abstract fun viewModelClass(): KClass<M>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${javaClass.simpleName} onCreate()")

        //INIT VIEWMODEL
        viewModel = getViewModel(viewModelClass())
        lifecycle.addObserver(viewModel)

        //INIT BINDING
        binding = DataBindingUtil.setContentView(this, layoutRes())
        binding.lifecycleOwner = this

        //INIT TOOLBAR
        initToolbar()
    }

    private fun initToolbar() {
        val toolbar: Toolbar? = binding.root.findViewById(R.id.toolbar)
        if (toolbar === null)
            return

        //SET OUR TOOLBAR AS ACTION BAR
        setSupportActionBar(toolbar)
        val titleFromManifest: String = supportActionBar?.title.toString()
        supportActionBar?.title = ""
        toolbar.title = titleFromManifest
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("${javaClass.simpleName} onDestroy()")

        lifecycle.removeObserver(viewModel)
        binding.unbind()
    }

    override fun onStart() {
        super.onStart()
        Timber.d("${javaClass.simpleName} onStart()")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("${javaClass.simpleName} onStop()")
    }
}