package jp.co.biglobe.isp.auth.domain.operatornouser;

import jp.co.biglobe.lib.publication.mask.MaskExecutor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@AllArgsConstructor
public class OperatorPassword {

    @Getter
    @NotNull
    private String value;

    @Override
    public String toString() {
        return MaskExecutor.execute(value, this.getClass());
    }
}
