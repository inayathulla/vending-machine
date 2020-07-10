package com.javator.vm.components;

import com.javator.vm.entities.Product;
import com.javator.vm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ApplicationStartUpRunner implements CommandLineRunner {

    boolean timeUp = false;

    @Autowired
    private ProductService service;

    @Override
    public void run(String... args) throws Exception {

        List<Product> products = service.findAll();
        Map<Integer, Integer> itemPriceMap = new HashMap<>();

        //Add all id's to set so if user enters invalid number then message can be shown
        products.forEach(product -> itemPriceMap.put(product.getPid(), product.getPamount()));

        int priceOfSelectedProduct = 0;
        //needed to maintain count of all coins
        int totalCoins = 0;

        while(true){
            //Timer is needed so if user is idle for stipulated time, then break loop so other user can
            //take his turn
            Timer timer1 = new Timer();
            Timer timer2 = new Timer();
            TimerTask task1= new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Times up!!");
                    timeUp = true;
                }
            };
            TimerTask task2= new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Times up!!");
                    timeUp = true;
                }
            };


            System.out.println("Choose a product, enter no. against product  ");
            products.forEach(product ->
                    System.out.println(product.getPid()+" " +product.getPname()+" "+product.getPamount()));

            Scanner scan = new Scanner(System.in);
            timer1.schedule(task1, 20*1000);

            if(timeUp){
                //If user has not entered selected option within time then
                timeUp = false;
                continue;
            }
            //If user enters other than number
            try{
                int num = scan.nextInt();
                //User has choosen within 20 seconds
                timer1.cancel();
                if(!itemPriceMap.containsKey(num)){
                    //custom exception can be raised later on
                    throw new Exception("Invalid no. selected");
                }else{
                    priceOfSelectedProduct = itemPriceMap.get(num);
                }
            }catch(Exception ex){
                //This will trigger if user enters anything other than no, if possible
                System.out.println("Invalid input try again");
                continue;
            }
            System.out.println("$ Insert coin(s) $");
            Scanner scan2 = new Scanner(System.in);
            timer2.schedule(task2, 20*1000);
            if(timeUp){
                //If user has not entered coin within time then
                timeUp = false;
                continue;
            }
            try{
                /**Assuming only number is inserted which corresponds to penny/nickel/dime/quarter
                 * penny = 1 cent, nickel = 5 cents, dime = 10 cents, quarter = 25 cents
                **/
                int coin = scan.nextInt();
                timer2.cancel();
                if(coin == priceOfSelectedProduct){
                    //Dispensing product
                    System.out.println(".... Enjoy your treat ....");
                }else if(coin > priceOfSelectedProduct){
                    //Dispensing product
                    System.out.println(".... Enjoy your treat ....");
                    //Dispensing remaining coins
                    System.out.println("... collect you surplus coins ...");
                }else{
                    //If coins entered is less than priceOfSelectedProduct
                    totalCoins += coin;

                    while(totalCoins < priceOfSelectedProduct){
                        Timer timer3=new Timer();
                        TimerTask task3= new TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("Times up!!");
                                timeUp = true;
                            }
                        };

                        System.out.println("$ Insert few more coin(s) $");
                        Scanner scan3 = new Scanner(System.in);
                        timer3.schedule(task3, 20*1000);

                        if(timeUp){
                            //If user has not entered coin within time then
                            timeUp = false;
                            //Insufficient coins and delayed time
                            System.out.println("*** Dispence all coins entered  ***");
                            continue;
                        }

                        int oneMoreCoin = scan3.nextInt();
                        timer3.cancel();
                        totalCoins += oneMoreCoin;
                    }
                }
            }catch(Exception ex){
                continue;
            }

        }
    }
}