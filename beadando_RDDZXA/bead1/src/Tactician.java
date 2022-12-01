/**
 *
 * @author RDDZXA
 * 
 * A taktikus játékosok osztályát reprezentálja.
 */
public class Tactician extends Player {

    /**
     * A taktikus játékos minden második vásárlást kihagy.
     * Ha igaz, akkor vásárolhat, különben nem.
     */
    protected boolean shouldI;
    
    /**
     * Létrehoz egy taktikus játékost a megadott névvel, és az adott mezőre állítja.
     * A játékosok 10000 Petákkal kezdenek.
     * Az első vásárlást meg van engedve.
     * @param name  név
     * @param f  mező, ahol áll
     */
    public Tactician(String name, Field f) {
        super(name, f);
        this.coin = 10000;
        this.shouldI = true;
    }
    
    /**
     *A taktikus játékos lép egyet a dobott értéknek megfelelően.
     * Megkeressük pontosan hol áll a taktikus játékos a táblán, majd a dobott értéknek megfelelő mezőre lép.
     * Ha a mező ingatlan, megnézi, hogy van-e már tulajdonosa.
     *  Ha nincs tulajdonosa az ingatlen mezőnek és vásárolhat, akkor megveszi.
     *  Ha ő maga a tulajdonosa az ingatlan mezőnek, nincs rajta még ház és vásárolhat, akkor vesz rá házat.
     *  Ha van tulajdonosa az ingatlannak, de nem ő az, akkor fizet a tulajdonosnak.
     * Ha a mező szolgáltatás, akkor kifizeti a költségeket.
     * Ha a mező szerencse, akkor begyűjti a jutalmat.
     * @param n  dobott szám
     * @param b  tábla
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     * @throws NoOwnerException  a nincs birtokosa a mezőnek
     * @throws DifferentOwnerException  ha más az ingatlan tulajdonosa
     * @throws HouseAlreadyThereException  ha már van ház a mezőn
     */
    @Override
    public void step(int n, Board b) throws AlreadyOwnedException, PlayerAlreadyOutException, NoOwnerException, DifferentOwnerException, HouseAlreadyThereException {
        int index = b.getFields().indexOf(this.standpoint);
        this.standpoint = b.stepTo(index, n);
        
        if(this.standpoint.isProperty()){
            if(!this.standpoint.isOwned()){
                if(this.shouldI){
                    this.buyProperty(this.standpoint);
                    this.shouldI = false;
                } else
                    this.shouldI = true;
            } else if(this.standpoint.getOwner().equals(this) && this.standpoint.isEmpty()){
                if(this.shouldI){
                    this.buyHouse(this.standpoint);
                    this.shouldI = false;
                } else
                    this.shouldI = true;
            } else {
                this.payToPlayer(this.standpoint.getOwner(), this.standpoint);
            }
        } else if(this.standpoint.isService()){
            this.payForService(this.standpoint);
        } else
            this.claimTheLuck(this.standpoint);
    }
    
    /**
     * A taktikus játékos lép egyet a dobott értéknek megfelelően.
     * A taktikus játékos először dob egyet.
     * Megkeressük pontosan hol áll a taktikus játékos a táblán, majd a dobott értéknek megfelelő mezőre lép.
     * Ha a mező ingatlan, megnézi, hogy van-e már tulajdonosa.
     *  Ha nincs tulajdonosa az ingatlen mezőnek és vásárolhat, akkor megveszi.
     *  Ha ő maga a tulajdonosa az ingatlan mezőnek, nincs rajta még ház és vásárolhat, akkor vesz rá házat.
     *  Ha van tulajdonosa az ingatlannak, de nem ő az, akkor fizet a tulajdonosnak.
     * Ha a mező szolgáltatás, akkor kifizeti a költségeket.
     * Ha a mező szerencse, akkor begyűjti a jutalmat.
     * @param b  tábla
     * @throws AlreadyOwnedException  ha már van birtokosa az adott mezőnek
     * @throws PlayerAlreadyOutException  ha már kiesett a játékos
     * @throws NoOwnerException  a nincs birtokosa a mezőnek
     * @throws DifferentOwnerException  ha más az ingatlan tulajdonosa
     * @throws HouseAlreadyThereException  ha már van ház a mezőn
     */
    @Override
    public void step(Board b) throws AlreadyOwnedException, PlayerAlreadyOutException, NoOwnerException, DifferentOwnerException, HouseAlreadyThereException {
        int n = this.roll();
        int index = b.getFields().indexOf(this.standpoint);
        this.standpoint = b.stepTo(index, n);
        
        if(this.standpoint.isProperty()){
            if(!this.standpoint.isOwned()){
                if(this.shouldI){
                    this.buyProperty(this.standpoint);
                    this.shouldI = false;
                } else
                    this.shouldI = true;
            } else if(this.standpoint.getOwner().equals(this) && this.standpoint.isEmpty()){
                if(this.shouldI){
                    this.buyHouse(this.standpoint);
                } else
                    this.shouldI = true;
            } else {
                this.payToPlayer(this.standpoint.getOwner(), this.standpoint);
            }
        } else if(this.standpoint.isService()){
            this.payForService(this.standpoint);
        } else
            this.claimTheLuck(this.standpoint);
    }
    
}
