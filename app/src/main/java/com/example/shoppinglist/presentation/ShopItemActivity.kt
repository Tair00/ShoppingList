package com.example.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopItemActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {
    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        if (savedInstanceState == null){
            launchRightMode()
        }
    }

    override fun onEditingFinished() {
        finish()
    }
    private fun parseIntent(){
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)){

            throw RuntimeException("Param screen mode is absent")
        }
        val mode  = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if(mode != MODE_EDIT && mode != MODE_ADD ){
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT){
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID,ShopItem.UNDEFINED_ID)
        }
    }
    private fun launchRightMode(){
        val fragment = when (intent.getStringExtra(EXTRA_SCREEN_MODE)) {
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID))
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .commit()
    }
    companion object {
        private const val EXTRA_SCREEN_MODE ="extra_mode"
        private const val EXTRA_SHOP_ITEM_ID ="extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""
        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID,shopItemId)
            return intent
        }

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
    }
}




//package com.example.shoppinglist.presentation
////
//import android.content.Context
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.RecyclerView
//import com.example.shoppinglist.R
//import com.example.shoppinglist.domain.ShopItem
//import com.google.android.material.textfield.TextInputLayout
//
//class ShopItemActivity : AppCompatActivity() {
//    //    private lateinit var viewModel: ShopItemViewModel
////    private lateinit var tilName: TextInputLayout
////    private lateinit var tilCount: TextInputLayout
////    private lateinit var etName: EditText
////    private lateinit var etCount: EditText
////    private lateinit var buttonSave: Button
////

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_shop_item)
//        parseIntent()
////        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
////        initViews()
////        addTextChangeListeners()
//        launchRightMode()
////        observeViewModel()
//    }

////    private fun observeViewModel(){
////        viewModel.errorInputCount.observe(this){
////            val message = if (it){
////                getString(R.string.error_input_count)
////            }else{
////                null
////            }
////            tilCount.error= message
////        }
////        viewModel.errorInputName.observe(this){
////            val message = if (it){
////                getString(R.string.error_input_name)
////            }else{
////                null
////            }
////            tilName.error= message
////        }
////        viewModel.shouldClouseScreen.observe(this){
////            finish()
////    }
//
////    private fun addTextChangeListeners(){
////        etName.addTextChangedListener(object :TextWatcher{
////            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
////
////            }
////
////            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
////                viewModel.resetErrorInputName()
////            }
////
////            override fun afterTextChanged(p0: Editable?) {
////
////            }
////
////        })
////        etCount.addTextChangedListener(object :TextWatcher{
////            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
////
////            }
////
////            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
////                viewModel.resetErrorInputCount()
////            }
////
////            override fun afterTextChanged(p0: Editable?) {
////
////            }
////
////        })
////    }
////    private fun launchEditMode(){
////        viewModel.getShopItem(shopItemId)
////        viewModel.shopItem.observe(this){
////            etName.setText(it.name)
////            etCount.setText(it.count.toString())
////        }
////        buttonSave.setOnClickListener{
////            viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
////        }
////
////    }
////    private fun launchAddMode(){
////        buttonSave.setOnClickListener{
////            viewModel.addShopItem(etName.text?.toString(), etCount.text?.toString())
////        }
////    }

//
////    private fun initViews(){
////        tilName = findViewById(R.id.til_name)
////        tilCount = findViewById(R.id.til_count)
////        etName = findViewById(R.id.et_name)
////        etCount = findViewById(R.id.et_count)
////        buttonSave = findViewById(R.id.save_button)
////
////    }
