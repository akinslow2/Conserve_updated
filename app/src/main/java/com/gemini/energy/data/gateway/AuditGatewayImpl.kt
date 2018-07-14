package com.gemini.energy.data.gateway

import com.gemini.energy.data.gateway.mapper.SystemMapper
import com.gemini.energy.data.repository.*
import com.gemini.energy.domain.entity.*
import com.gemini.energy.domain.gateway.AuditGateway
import io.reactivex.Observable

class AuditGatewayImpl(
        private val auditRepository: AuditRepository,
        private val zoneRepository: ZoneRepository,
        private val typeRepository: TypeRepository,
        private val featureRepository: FeatureRepository,
        private val computableRepository: ComputableRepository) : AuditGateway {

    private val mapper = SystemMapper()

    /*Audit*/
    override fun getAuditList(): Observable<List<Audit>> =
            auditRepository.getAll()
                    .doOnError { println("Audit Get Error") }
                    .map { it.map { mapper.toEntity(it) } }

    override fun saveAudit(audit: Audit): Observable<Unit> = auditRepository.save(audit)


    /*Zone*/
    override fun getZoneList(auditId: Int): Observable<List<Zone>> =
            zoneRepository.getAllByAudit(auditId)
                    .map { it.map { mapper.toEntity(it) } }

    override fun saveZone(zone: Zone): Observable<Unit> = zoneRepository.save(zone)


    /*Audit Zone Type*/
    override fun getAuditScopeList(zoneId: Int, type: String): Observable<List<Type>> =
            typeRepository.getAllTypeByZone(zoneId, type)
                    .map { it.map { mapper.toEntity(it) } }

    override fun saveAuditScope(auditScope: Type) = typeRepository.save(auditScope)


    /*Feature Data*/
    override fun getFeature(auditId: Int): Observable<List<Feature>> =
            featureRepository.getAllByAudit(auditId)
                    .map { it.map { mapper.toEntity(it) } }

    override fun getFeatureByType(zoneId: Int): Observable<List<Feature>> =
            featureRepository.getAllByType(zoneId)
                    .map { it.map { mapper.toEntity(it) } }

    override fun saveFeature(feature: List<Feature>): Observable<Unit> = featureRepository.save(feature)

    override fun deleteFeature(feature: List<Feature>): Observable<Unit> =
            featureRepository.delete(feature)


    /*Computable*/
    override fun getComputable(): Observable<List<Computable<*>>> =
        computableRepository.getAllComputable()
                .map { it.map { mapper.toEntity(it) } }

}