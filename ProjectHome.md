# Goals #

Many software systems provide configuration options for users to tailor their functional behavior as well as non-functional properties (e.g., performance, cost, and energy consumption). Configuration options relevant to users are often called _features_. Each _variant_ derived from a configurable software system can be represented as a selection of features, called a _configuration_.

Performance (e.g., response time or throughput) is one of the most important non-functional properties, because it directly affects user perception and cost. To find an optimal configuration to meet a specific performance goal, it is crucial for developers and IT administrators to understand the correlation between feature selections and performance.

We investigate a practical approach that mines such a correlation from a sample of measured configurations, specifies the correlation as an explicit performance prediction model, and then uses the model to predict the performance of other unmeasured configurations.

# Topics #

## Variability-Aware Performance Prediction via Statistical Learning ##

It is usually infeasible to measure all configurations of a software system, because even a small-scale software system can give rise to an exponential number of configurations, due to feature combinatorics. Moreover, the cost of measurement may be high (e.g., executing a complex benchmark). Therefore, in practice, often only a limited set of configurations can be measured. We denote these configurations along with their performance measurements as a _sample_, and all configurations of a software system along with their performance as the _whole population_. The challenge is how to use a _small_ (e.g., linear in the number of features) sample to accurately predict the performance of other configurations in the whole population, especially when features interact.

We reduce the problem of variability-aware performance prediction to a _non-linear_ _regression_ problem. We experiment with various Machine Learning or Statistical Learning techniques, such as Classification And Regression Trees (CARTs), to address the problem and to model the correlation between feature selections and performance.

Our approach works _automatically_ and _incrementally_ with random samples, such that one can use it to produce predictions, starting with a small random sample, and subsequently extend it when further measurements are available; it considers _all_ features of a software system and identifies the performance-relevant ones; it treats the selected and deselected features in a configuration _equally_, to describe the correlation between feature selections and performance; and it can be _easily_ implemented and deployed in practice, without additional effort to detect feature interactions---an inherently challenging task.

Empirical results on six real-world case studies demonstrate an average of 94% prediction accuracy based on small random samples.

## Similarity between a Sample and the Whole Population ##

The above approach works because (1) the evaluated dataset fits well in the non-linear regression model we use and (2) the sample we use reflects the important characteristics of the whole population. To explore the similarity between a sample and the whole population, we conduct a comparative analysis of performance distributions via histograms. Further, we design metrics to quantify such a similarity between two configurations, between a configuration and a sample, or between two samples (including the whole population).

The challenge is that designing a comprehensive similarity metric has to consider feature selections and non-functional properties _together_. Thus, we encounter _heterogeneous_ variables, such as categorical or numeric feature selections and numeric non-functional properties. Moreover, numeric variables may have _different_ _scales_, i.e., different ranges of values. These heterogeneous or different-scale variables may cause unbalanced domination in one metric.

We treat categorical and numeric variables separately and then combine them together. We design two distance (a distance can be converted to a similarity straightforwardly using a monotonic decreasing function) metrics. One is used when the whole population is known and its calculation follows the principle of _chi-squared_ _test_. Another is used when the whole population is unknown and it exploits _cosine_ _similarity_ and _spatial_ _overlap_.

## Progressive/Adaptive Sampling Schemes ##

A sample is _representative_ if using such a sample will work almost as well as using the whole population. However, it is often difficult to find such a representative sample. The best that we can do is to choose a sampling scheme that guarantees a high probability of getting a representative sample, which involves choosing the proper sample size and sampling techniques. To determine the proper sample size, we follow the idea of progressive or adaptive sampling schemes. That is, the schemes start with a small sample, and then increase the sample size until a sample of “sufficient” size is obtained. To judge whether the current sample size is sufficient, we need an evaluation function to estimate the quality of the current sample. For performance prediction, we can use the prediction accuracy as the evaluation function. If a sample attains high accuracy, then the sample size is sufficient.

# Publications #
  * Jianmei Guo, Krzysztof Czarnecki, Sven Apel, Norbert Siegmund, and Andrzej Wasowski. [Variability-Aware Performance Prediction: A Statistical Learning Approach](http://gsd.uwaterloo.ca/node/532). To appear in the proceedings of 28th IEEE/ACM International Conference on Automated Software Engineering (ASE 2013). Acceptance rate (full papers): 51/317 ≈ 16%.
  * Jianmei Guo, Krzysztof Czarnecki, Sven Apel, Norbert Siegmund, and Andrzej Wasowski. [Why CART Works for Variability-Aware Performance Prediction? An Empirical Study on Performance Distributions](http://gsd.uwaterloo.ca/publications/view/527). Technical Report, GSDLAB-TR-2013-04-02, Generative Software Development Lab, University of Waterloo, 2013.
  * Jianmei Guo, Krzysztof Czarnecki, Sven Apel, Norbert Siegmund, and Andrzej Wasowski. [Variability-Aware Performance Modeling: A Statistical Learning Approach](http://gsd.uwaterloo.ca/publications/view/484). Technical Report, GSDLAB-TR-2012-08-18, Generative Software Development Lab, University of Waterloo, 2012.

# People #
  * [Jianmei Guo (University of Waterloo, Canada)](http://gsd.uwaterloo.ca/gjm)
  * [Krzysztof Czarnecki (University of Waterloo, Canada)](http://gsd.uwaterloo.ca/kczarnec)
  * [Sven Apel (University of Passau, Germany)](http://www.infosun.fim.uni-passau.de/spl/apel/)
  * [Norbert Siegmund (University of Passau, Germany)](http://www.infosun.fim.uni-passau.de/spl/people-nsiegmund.php)
  * [Andrzej Wąsowski (IT University of Copenhagen, Denmark)](http://www.itu.dk/~wasowski/)
  * [Sebastian Fischmeister (University of Waterloo, Canada)](https://uwaterloo.ca/embedded-software-group/people-profiles/sebastian-fischmeister)
  * [Augusto Born de Oliveira (University of Waterloo, Canada)](https://uwaterloo.ca/embedded-software-group/about/people/a3oliveira)
  * [Jean-Christophe Petkovich (University of Waterloo, Canada)](https://uwaterloo.ca/embedded-software-group/about/people/j2petkov)
  * [Sergiy Kolesnikov (University of Passau, Germany)](http://www.infosun.fim.uni-passau.de/spl/people-kolesnikov.php)
  * [Pavel Valov (University of Waterloo, Canada)](http://gsd.uwaterloo.ca/pvalov)