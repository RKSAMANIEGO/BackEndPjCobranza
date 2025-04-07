package com.example.demo.model;

import java.time.LocalDate;
import com.example.demo.model.enumerated.EnumMetodoPago;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Table(name="pagos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pago_id")
	private Integer pagoId;

	@Column(name="monto_pago")
	private Double montoPago;
	
	@Default
	@Column(name="fecha_pago")
	private LocalDate fechaPago= LocalDate.now();
		
	@Column(name="metodo_pago")
	@Enumerated(EnumType.STRING)
	private EnumMetodoPago metodoPago;
	
	@ManyToOne
	@JoinColumn(name="prestamo_id" , foreignKey = @ForeignKey(foreignKeyDefinition = (
	"foreign key (prestamo_id) references prestamos(orestamo_id) ")))
	private CPrestamo prestamo;
}
