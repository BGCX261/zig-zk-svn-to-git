package com.twinstorm.zkskeleton.persistence.user;

import com.twinstorm.zkskeleton.persistence.common.hibernate.AbstractCrudDao;
import com.twinstorm.zkskeleton.domain.user.CustomUser;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl extends AbstractCrudDao<CustomUser> implements UserDao{

    public CustomUser findByUsername(String name) {
    DetachedCriteria criteria = DetachedCriteria.forClass(CustomUser.class);
    criteria.add(Property.forName("username").eq(name));

    List<CustomUser> users = getHibernateTemplate().findByCriteria(criteria);

    CustomUser user = (users != null && ! users.isEmpty()) ? users.get(0) : null;
    if (user != null) {
      getHibernateTemplate().initialize(user.getRoles());
    }
    return user;
  }

  public List<CustomUser> search(CustomUser example) {
    DetachedCriteria criteria = createCriteria();

    if ( ! StringUtils.isEmpty(example.getUsername())) {
      criteria.add(Restrictions.ilike("username", example.getUsername(), MatchMode.START));
    }

    if ( ! StringUtils.isEmpty(example.getFirstName())) {
      criteria.add(Restrictions.ilike("firstName", example.getUsername(), MatchMode.START));
    }

    if ( ! StringUtils.isEmpty(example.getSurname())) {
      criteria.add(Restrictions.ilike("surname", example.getUsername(), MatchMode.START));
    }

    return getHibernateTemplate().findByCriteria(criteria);
  }

  public List<CustomUser> search(String username, String firstName, String surname) {
    DetachedCriteria criteria = DetachedCriteria.forClass(CustomUser.class);

    if ( ! StringUtils.isEmpty(username)) {
      criteria.add(Restrictions.ilike("username", username, MatchMode.ANYWHERE));
    }

    if ( ! StringUtils.isEmpty(firstName)) {
      criteria.add(Restrictions.ilike("firstName", firstName, MatchMode.ANYWHERE));
    }

    if ( ! StringUtils.isEmpty(surname)) {
      criteria.add(Restrictions.ilike("surname", surname, MatchMode.ANYWHERE));
    }

    return getHibernateTemplate().findByCriteria(criteria);
  }
}
