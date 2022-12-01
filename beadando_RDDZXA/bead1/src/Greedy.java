
/**
 *
 * @author RDDZXA
 * 
 * A mohó játékosok osztályát reprezentálja.
 */
public class Greedy extends Player {
    
    /**
     * Létrehoz egy mohó játékost a megadott névvel, és az adott mezőre állítja.
     * A játékosok 10000 Petákkal kezdenek.
     * @param name  név
     * @param f  mező, ahol áll
     */
    public Greedy(String name, Field f) {
        super(name, f);
        this.coin = 10000;
    }
    
    /**
     * A mohó játékos lép egyet a dobott értéknek megfelelően.
     * Megkeressük pontosan hol áll a mohó játékos a táblán, majd a dobott értéknek megfelelő mezőre lép.
     * Ha a mező ingatlan, megnézi, hogy van-e már tulajdonosa.
     *  Ha nincs tulajdonosa az ingatlen mezőnek, akkor megveszi.
     *  Ha ő maga a tulajdonosa az ingatlan mezőnek és nincs rajta még ház, akkor vesz rá házat.
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
                this.buyProperty(this.standpoint);
            } else if(this.standpoint.getOwner().equals(this) && this.standpoint.isEmpty()){
                this.buyHouse(this.standpoint);
            } else 
                this.payToPlayer(this.standpoint.getOwner(), this.standpoint);
        } else if(this.standpoint.isService()){
            this.payForService(this.standpoint);
        } else
            this.claimTheLuck(this.standpoint);
    }
    
    /**
     *A mohó játékos lép egyet a dobott értéknek megfelelően.
     * A mohó játékos először dob egyet.
     * Megkeressük pontosan hol áll a mohó játékos a táblán, majd a dobott értéknek megfelelő mezőre lép.
     * Ha a mező ingatlan, megnézi, hogy van-e már tulajdonosa.
     *  Ha nincs tulajdonosa az ingatlen mezőnek, akkor megveszi.
     *  Ha ő maga a tulajdonosa az ingatlan mezőnek és nincs rajta még ház, akkor vesz rá házat.
     *  Ha van tulajdonosa az ingatlannak, de nem ő az, akkor fizet a tulajdonosnak.
     * Ha a mező szolgáltatás, akkor kifizeti a költségeket.
     * Ha a mező szerencse, akkor begyűjti a jutalmat.ó
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
                this.buyProperty(this.standpoint);
            } else if(this.standpoint.getOwner().equals(this) && this.standpoint.isEmpty()){
                this.buyHouse(this.standpoint);
            } else 
                this.payToPlayer(this.standpoint.getOwner(), this.standpoint);
        } else if(this.standpoint.isService()){
            this.payForService(this.standpoint);
        } else
            this.claimTheLuck(this.standpoint);
    }
    
}
