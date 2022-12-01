/**
 *
 * @author RDDZXA
 * 
 * Az óvatos játékosok osztályát reprezentálja.
 */
public class Cautious extends Player{

    /**
     * Létrehoz egy óvatos játékost a megadott névvel, és az adott mezőre állítja.
     * A játékosok 10000 Petákkal kezdenek.
     * @param name  név
     * @param f  mező, ahol áll
     */
    public Cautious(String name, Field f) {
        super(name, f);
        this.coin = 10000;
    }
    
    /**
     * Mivel egy óvatos játékosról van szó csak a tőkéjének a felére költekezik.
     * Ha kivonjuk a költendő összeget
     * @param n  az elköltendő összeg
     * @return  eldönti, hogy vásárolhat-e 
     */
    public boolean shouldIBuyIt(int n){
        return (n <= this.coin/2);
    }
    
    /**
     * Az óvatos játékos lép egyet a dobott értéknek megfelelően.
     * Megkeressük pontosan hol áll az óvatos játékos a táblán, majd a dobott értéknek megfelelő mezőre lép.
     * Ha a mező ingatlan, megnézi, hogy van-e már tulajdonosa.
     *  Ha nincs tulajdonosa az ingatlen mezőnek és megmarad a tőkéjének legalább a fele, akkor megveszi.
     *  Ha ő maga a tulajdonosa az ingatlan mezőnek, nincs rajta még ház és megmarad a tőkéjének legalább a fele, akkor vesz rá házat.
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
                if(this.shouldIBuyIt(1000)){
                    this.buyProperty(this.standpoint);
                }
            } else if(this.standpoint.isOwned()){
                if(this.standpoint.getOwner().equals(this) && this.standpoint.isEmpty()){
                    if(this.shouldIBuyIt((4000))){
                        this.buyHouse(this.standpoint);
                    }
                } else {
                    this.payToPlayer(this.standpoint.getOwner(), this.standpoint);
                }
            }
        } else if(this.standpoint.isService()){
            this.payForService(this.standpoint);
        } else
            this.claimTheLuck(this.standpoint);
        
        
    }
    
    /**
     * Az óvatos játékos lép egyet a dobott értéknek megfelelően.
     * Az óvatos játékos először dob egyet.
     * Megkeressük pontosan hol áll az óvatos játékos a táblán, majd a dobott értéknek megfelelő mezőre lép.
     * Ha a mező ingatlan, megnézi, hogy van-e már tulajdonosa.
     *  Ha nincs tulajdonosa az ingatlen mezőnek és megmarad a tőkéjének legalább a fele, akkor megveszi.
     *  Ha ő maga a tulajdonosa az ingatlan mezőnek, nincs rajta még ház és megmarad a tőkéjének legalább a fele, akkor vesz rá házat.
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
                if(this.shouldIBuyIt(500)){
                    this.buyProperty(this.standpoint);
                }
            } else if(this.standpoint.getOwner().equals(this) && this.standpoint.isEmpty()){
                if(this.shouldIBuyIt((2000))){
                    this.buyHouse(this.standpoint);
                }
            } else {
                this.payToPlayer(this.standpoint.getOwner(), this.standpoint);
            }
        } else if(this.standpoint.isService()){
            this.payForService(this.standpoint);
        } else
            this.claimTheLuck(this.standpoint);
    }
}
