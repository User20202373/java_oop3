package generic.ch03;

/**
 * <T extends 클래스> 를 사용하면 현재 코드에서 Material을 상속받은 자식 클래스만
 * 대체 문자열에 들어올 수 있다.
 */
public class GenericExtendsPrinter<T extends Material> {

    T material;

    public T getMaterial() {
        return material;
    }

    public void setMaterial(T material) {
        this.material = material;
    }


}
