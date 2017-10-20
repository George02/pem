/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.api;

import info.tgeorge.asf.common.filters.DateRangeFilter;
import info.tgeorge.asf.common.filters.Filter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author george
 */
public class FilterUtil {

    public static Criterion getCriterion(Filter filter) throws ParseException {
        if (null == filter.getType()) {
            throw new RuntimeException("null filter type");
        }

        switch (filter.getOperator()) {
            case EQUAL_TO:
                return Restrictions.eq(filter.getKey(), filter.getValue());
            case GREATER_THAN:
                return Restrictions.gt(filter.getKey(), filter.getValue());
            case LOWER_THAN:
                return Restrictions.lt(filter.getKey(), filter.getValue());
            case LIKE:
                return Restrictions.like(filter.getKey(), filter.getValue());
            case BETWEEN:
                DateRangeFilter drf = (DateRangeFilter) filter.getValue();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date dateFrom = formatter.parse(drf.getFrom());
                Date dateTo = formatter.parse(drf.getTo());

                return Restrictions.between(filter.getKey(), dateFrom, dateTo);

            default:
                throw new RuntimeException("Unknown filter operator");
        }
    }

//    private static Criterion getStringFilterWhereClause(Filter filter) {
//        StringBuilder sbWhere = new StringBuilder();
//
//        sbWhere.append(filter.getKey());
//        sbWhere.append(" ");
//        sbWhere.append(FilterUtil.opToHQLOperator(filter.getOperator()));
//        sbWhere.append(" ");
//        sbWhere.append("'").append(String.valueOf(filter.getValue())).append("'");
//
//        return Restrictions.like(propertyName, filter)
//    }
//
//    private static Criterion getDoubleFilterWhereClause(Filter filter) {
//        StringBuilder sbWhere = new StringBuilder();
//
//        sbWhere.append(filter.getKey());
//        sbWhere.append(" ");
//        sbWhere.append(FilterUtil.opToHQLOperator(filter.getOperator()));
//        sbWhere.append(" ");
//        sbWhere.append(String.valueOf(filter.getValue()));
//
//        return sbWhere.toString();
//    }
//
//    private static Criterion getBooleanFilterWhereClause(Filter filter) {
//        Boolean boolValue = (Boolean) filter.getValue();
//
//        StringBuilder sbWhere = new StringBuilder();
//
//        sbWhere.append(filter.getKey());
//        if (boolValue) {
//            sbWhere.append(" ").append(" = ").append(" 1 ");
//        } else {
//            sbWhere.append(" ").append(" = ").append(" 0 ");
//        }
//
//        return sbWhere.toString();
//    }
//
//    private static Criterion getDateRangeFilterWhereClause(Filter filter) {
//        DateRangeFilter drf = (DateRangeFilter) filter.getValue();
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String frmDate = format.parse(drf.getFrom());
//        String enDate = format.parse(drf.getTo());
//
//        StringBuilder sbWhere = new StringBuilder();
//
//        sbWhere.append(filter.getKey());
//        sbWhere.append(" ");
//        sbWhere.append(FilterUtil.opToHQLOperator(filter.getOperator()));
//        sbWhere.append(" ");
//        sbWhere.append("'").append(frmDate).append("'");
//        sbWhere.append(" AND ");
//        sbWhere.append("'").append(frmDate).append("'");
//
//        return sbWhere.toString();
//    }
//
//    private static String opToHQLOperator(FilterOperator op) {
//        switch (op) {
//            case BETWEEN:
//                return "between";
//            case EQUAL_TO:
//                return "=";
//            case GREATER_THAN:
//                return ">";
//            case LOWER_THAN:
//                return "<";
//            case LIKE:
//                return "like";
//            default:
//                throw new RuntimeException("Unable to convert filter operator to HQL operator. ");
//        }
//    }
//
//    public static Criterion getCriterion(Filter filter) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
