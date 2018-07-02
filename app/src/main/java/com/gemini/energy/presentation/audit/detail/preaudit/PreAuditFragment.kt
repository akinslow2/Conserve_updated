package com.gemini.energy.presentation.audit.detail.preaudit

import com.gemini.energy.R
import com.gemini.energy.presentation.audit.list.model.AuditModel
import com.gemini.energy.presentation.base.BaseFormFragment

class PreAuditFragment : BaseFormFragment() {

    private var auditModel: AuditModel? = null

    override fun resourceId(): Int {
        return R.raw.preaudit_sample
    }

    override fun getAuditId(): Int? {
        return auditModel?.id
    }

    override fun getZoneId(): Int? {
        return null
    }

    fun setAuditModel(auditModel: AuditModel) {
        this.auditModel = auditModel
    }


    companion object {

        fun newInstance(): PreAuditFragment {
            return PreAuditFragment()
        }

        private const val TAG = "PreAuditFragment"
    }

}