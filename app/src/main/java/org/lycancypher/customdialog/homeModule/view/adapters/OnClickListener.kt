package org.lycancypher.customdialog.homeModule.view.adapters

interface OnClickListener {
    fun onClickSuccess()
    fun onClickError()
    fun onClickAlert()
    fun onClickConfirm()
    fun onClickInput(text: Boolean)
}