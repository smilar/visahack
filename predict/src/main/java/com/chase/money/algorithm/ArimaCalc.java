package com.chase.money.algorithm;

import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;

public class ArimaCalc {

    public static String getResults(double[] inDataArray) {

        {
            // Prepare input timeseries data.
            //double[] dataArray = new double[]{1, 2,3,4,5,6,7,8,9,10,11,12};
           // double[] dataArray = new double[]{2,4,6,8,10,12,14,16,18,20,22,24};
            double[] dataArray = new double[]{1, 5,9,13,17,21,25,29,33,37,41,45};
           // double[] dataArray = new double[]{1, 2,3,4,5,6,7,8,9,10,11,12};

            // Set ARIMA model parameters.
            int p = 3;
            int d = 0;
            int q = 3;
            int P = 1;
            int D = 1;
            int Q = 0;
            int m = 0;
            int forecastSize = 1;

            // Obtain forecast result. The structure contains forecasted values and performance metric etc.

            ArimaParams paramsForecast = new ArimaParams(p, d, q, P, D, Q, m);
            ForecastResult forecastResult = Arima.forecast_arima(inDataArray, forecastSize, paramsForecast);

            // Read forecast values
            double[] forecastData = forecastResult.getForecast(); // in this example, it will return { 2 }

            // You can obtain upper- and lower-bounds of confidence intervals on forecast values.
            // By default, it computes at 95%-confidence level. This value can be adjusted in ForecastUtil.java
            double[] uppers = forecastResult.getForecastUpperConf();
            double[] lowers = forecastResult.getForecastLowerConf();

            // You can also obtain the root mean-square error as validation metric.
            double rmse = forecastResult.getRMSE();

            // It also provides the maximum normalized variance of the forecast values and their confidence interval.
            double maxNormalizedVariance = forecastResult.getMaxNormalizedVariance();

            // Finally you can read log messages.
            String log = forecastResult.getLog();

            return Double.toString(forecastData[0]);
        }

    }

    public static void main (String[]args){
        double[] dataArray = new double[]{1, 5,9,13,17,21,25,29,33,37,41,45};
        System.out.println(ArimaCalc.getResults(dataArray));
    }

}

