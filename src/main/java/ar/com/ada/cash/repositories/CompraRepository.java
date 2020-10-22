package ar.com.ada.cash.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.ada.cash.entities.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

  @Query(value = "select * from Compra co LIMIT ?, ?", nativeQuery = true)
  List<Compra> findAllByOffset(Integer offSet, Integer count);

  @Query(value = "select * from usuario u inner join Compra co on u.usuario_id = co.usuario_id where u.usuario_id = ? LIMIT ?, ?", nativeQuery = true)
  List<Compra> findAllByOffsetByusuario(Integer usuarioId, Integer offSet, Integer count);

  @Query("select count(co) from Compra co where co.usuario.id = :usuarioId")
  long countByusuarioId(Integer usuarioId);

}
