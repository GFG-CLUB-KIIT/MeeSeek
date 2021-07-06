package co.kit.gfg.chatapp.mapper

interface EntityMapper<EntityMapper, DomainModel> {
    fun mapFromEntity(entity: EntityMapper): DomainModel?
    fun mapToEntity(domainModel: DomainModel): EntityMapper?
}