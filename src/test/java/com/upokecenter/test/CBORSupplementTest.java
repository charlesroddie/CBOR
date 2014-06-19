package com.upokecenter.test;
/*
Written by Peter O. in 2014.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://upokecenter.com/d/
 */

import java.io.*;
import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.util.*;
import com.upokecenter.cbor.*;

  public class CBORSupplementTest {
    @Test
    public void TestCBORObjectAbs() {
      Assert.assertEquals(
        CBORObject.FromObject(2),
        CBORObject.FromObject(-2).Abs());
      Assert.assertEquals(
        CBORObject.FromObject(2),
        CBORObject.FromObject(2).Abs());
      Assert.assertEquals(
        CBORObject.FromObject(2.5),
        CBORObject.FromObject(-2.5).Abs());
      Assert.assertEquals(
        CBORObject.FromObject(ExtendedDecimal.FromString("6.63")),
        CBORObject.FromObject(ExtendedDecimal.FromString("-6.63")).Abs());
      Assert.assertEquals(
        CBORObject.FromObject(ExtendedFloat.FromString("2.75")),
        CBORObject.FromObject(ExtendedFloat.FromString("-2.75")).Abs());
      Assert.assertEquals(
        CBORObject.FromObject(ExtendedRational.FromDouble(2.5)),
        CBORObject.FromObject(ExtendedRational.FromDouble(-2.5)).Abs());
    }

    @Test
    public void IncorrectDecimalFrac() {
      byte[] bytes;
      // String instead of array
      bytes = new byte[] {  (byte)0xc4, 0x61, 0x41  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      // number instead of array
      bytes = new byte[] {  (byte)0xc4, 0x00  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xc4, (byte)0x81, 0, 0  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xc4, (byte)0x82, 0, 0x61, 0x41  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xc4, (byte)0x82, 0x61, 0x41, 0  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xc4, (byte)0x83, 0, 0, 0  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestPrecisionContextArgValidation() {
      try {
        new PrecisionContext(-1, Rounding.HalfEven, 0, 0, false);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        new PrecisionContext(0, Rounding.HalfEven, 0, -1, false);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void IncorrectBigFloat() {
      byte[] bytes;
      // String instead of array
      bytes = new byte[] {  (byte)0xc5, 0x61, 0x41  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      // number instead of array
      bytes = new byte[] {  (byte)0xc5, 0x00  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xc5, (byte)0x81, 0, 0  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xc5, (byte)0x82, 0, 0x61, 0x41  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xc5, (byte)0x82, 0x61, 0x41, 0  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xc5, (byte)0x83, 0, 0, 0  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCBORObjectSign() {
      try {
        int sign = CBORObject.True.signum();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        int sign = CBORObject.False.signum();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        int sign = CBORObject.NewArray().signum();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        int sign = CBORObject.NewMap().signum();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCBORObjectCompareTo() {
      Assert.assertEquals(1, CBORObject.True.compareTo(null));
      Assert.assertEquals(1, CBORObject.False.compareTo(null));
      Assert.assertEquals(1, CBORObject.Null.compareTo(null));
      Assert.assertEquals(1, CBORObject.NewArray().compareTo(null));
      Assert.assertEquals(1, CBORObject.NewMap().compareTo(null));
      Assert.assertEquals(1, CBORObject.FromObject(100).compareTo(null));
      Assert.assertEquals(1, CBORObject.FromObject(Double.NaN).compareTo(null));
      CBORTest.CompareTestLess(CBORObject.Undefined, CBORObject.Null);
      CBORTest.CompareTestLess(CBORObject.Null, CBORObject.False);
      CBORTest.CompareTestLess(CBORObject.False, CBORObject.True);
      CBORTest.CompareTestLess(CBORObject.False, CBORObject.FromObject(0));
      CBORTest.CompareTestLess(CBORObject.False, CBORObject.FromSimpleValue(0));
      CBORTest.CompareTestLess(CBORObject.FromSimpleValue(0), CBORObject.FromSimpleValue(1));
      CBORTest.CompareTestLess(CBORObject.FromObject(0), CBORObject.FromObject(1));
      CBORTest.CompareTestLess(CBORObject.FromObject(0.0f), CBORObject.FromObject(1.0f));
      CBORTest.CompareTestLess(CBORObject.FromObject(0.0), CBORObject.FromObject(1.0));
    }

    @Test
    public void TestCBORObjectAsInt32() {
      try {
        CBORObject.True.AsInt32();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.False.AsInt32();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewArray().AsInt32();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewMap().AsInt32();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCBORObjectIsIntegral() {
      CBORObject cbor = CBORObject.True;
      if(cbor.isIntegral())Assert.fail();
      cbor = CBORObject.False;
      if(cbor.isIntegral())Assert.fail();
      if(CBORObject.NewArray().isIntegral())Assert.fail();
      if(CBORObject.NewMap().isIntegral())Assert.fail();
      if(!(CBORObject.FromObject(0).isIntegral()))Assert.fail();
      if(!(CBORObject.FromObject(BigInteger.ONE.shiftLeft(63)).isIntegral()))Assert.fail();
      if(!(CBORObject.FromObject(BigInteger.ONE.shiftLeft(64)).isIntegral()))Assert.fail();
      if(!(CBORObject.FromObject(BigInteger.ONE.shiftLeft(80)).isIntegral()))Assert.fail();
      if(!(CBORObject.FromObject(ExtendedDecimal.FromString("4444e+800")).isIntegral()))Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.FromString("4444e-800")).isIntegral())Assert.fail();
      if(CBORObject.FromObject(2.5).isIntegral())Assert.fail();
      if(CBORObject.FromObject(999.99).isIntegral())Assert.fail();
      if(CBORObject.FromObject(Double.POSITIVE_INFINITY).isIntegral())Assert.fail();
      if(CBORObject.FromObject(Double.NEGATIVE_INFINITY).isIntegral())Assert.fail();
      if(CBORObject.FromObject(Double.NaN).isIntegral())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.PositiveInfinity).isIntegral())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.NegativeInfinity).isIntegral())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.NaN).isIntegral())Assert.fail();
    }

    private static final class FakeConverter implements ICBORConverter<java.net.URI> {
      public CBORObject ToCBORObject(java.net.URI obj) {
        throw new UnsupportedOperationException();
      }
    }

    @Test
    public void TestCBORObjectCount() {
      Assert.assertEquals(0, CBORObject.True.size());
      Assert.assertEquals(0, CBORObject.False.size());
      Assert.assertEquals(0, CBORObject.NewArray().size());
      Assert.assertEquals(0, CBORObject.NewMap().size());
    }

    @Test
    public void TestCBORObjectGetByteString() {
      try {
        CBORObject.True.GetByteString();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.FromObject(0).GetByteString();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.FromObject("test").GetByteString();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.False.GetByteString();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewArray().GetByteString();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewMap().GetByteString();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(CBORObject.Null, CBORObject.FromObject((ExtendedRational)null));
      Assert.assertEquals(CBORObject.Null, CBORObject.FromObject((ExtendedDecimal)null));
      Assert.assertEquals(CBORObject.FromObject(10), CBORObject.FromObject(ExtendedRational.Create(10, 1)));
      try {
        CBORObject.FromObject(ExtendedRational.Create(10, 2));
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCBORObjectGetIntIndex() {
      CBORObject cbor = CBORObject.True;
      try {
        CBORObject cbor2 = cbor.get(0);
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      cbor = CBORObject.False;
      try {
        CBORObject cbor2 = cbor.get(0);
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      cbor = CBORObject.FromObject(0);
      try {
        CBORObject cbor2 = cbor.get(0);
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      cbor = CBORObject.FromObject(2);
      try {
        CBORObject cbor2 = cbor.get(0);
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      cbor = CBORObject.NewArray();
      try {
        CBORObject cbor2 = cbor.get(0);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCBORObjectArgumentValidation() {
      try {
        CBORObject.FromObjectAndTag(CBORObject.Null, -1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.FromObjectAndTag(CBORObject.Null, 999999);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.FromObject('\udddd');
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(CBORObject.Null, CBORObject.FromObject((byte[])null));
      Assert.assertEquals(CBORObject.Null, CBORObject.FromObject((CBORObject[])null));
      Assert.assertEquals(CBORObject.True, CBORObject.FromObject(true));
      Assert.assertEquals(CBORObject.False, CBORObject.FromObject(false));
      Assert.assertEquals(CBORObject.FromObject(8), CBORObject.FromObject((byte)8));
      try {
        CBORObject.DecodeFromBytes(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {   });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  0x1e  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xfe  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xff  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.AddConverter(null, new FakeConverter());
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.AddConverter(String.class, new FakeConverter());
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.AddTagHandler(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.True.Negate();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.False.Negate();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewArray().Negate();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewMap().Negate();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.True.Abs();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.False.Abs();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewArray().Abs();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewMap().Abs();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.True.AsExtendedRational();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.False.AsExtendedRational();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewArray().AsExtendedRational();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.NewMap().AsExtendedRational();
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestCBORObjectIsFinite() {
      CBORObject cbor = CBORObject.True;
      if(cbor.isFinite())Assert.fail();
      cbor = CBORObject.False;
      if(cbor.isFinite())Assert.fail();
      if(CBORObject.NewArray().isFinite())Assert.fail();
      if(CBORObject.NewMap().isFinite())Assert.fail();
      if(!(CBORObject.FromObject(0).isFinite()))Assert.fail();
      if(!(CBORObject.FromObject(2.5).isFinite()))Assert.fail();
      if(CBORObject.FromObject(Double.POSITIVE_INFINITY).isFinite())Assert.fail();
      if(CBORObject.FromObject(Double.NEGATIVE_INFINITY).isFinite())Assert.fail();
      if(CBORObject.FromObject(Double.NaN).isFinite())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.PositiveInfinity).isFinite())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.NegativeInfinity).isFinite())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.NaN).isFinite())Assert.fail();
    }

    @Test
    public void TestCBORObjectCanTruncatedIntFitInInt32() {
      if(CBORObject.True.CanTruncatedIntFitInInt32())Assert.fail();
      if(CBORObject.False.CanTruncatedIntFitInInt32())Assert.fail();
      if(CBORObject.NewArray().CanTruncatedIntFitInInt32())Assert.fail();
      if(CBORObject.NewMap().CanTruncatedIntFitInInt32())Assert.fail();
      if(!(CBORObject.FromObject(0).CanTruncatedIntFitInInt32()))Assert.fail();
      if(!(CBORObject.FromObject(2.5).CanTruncatedIntFitInInt32()))Assert.fail();
      if(!(CBORObject.FromObject(Integer.MIN_VALUE).CanTruncatedIntFitInInt32()))Assert.fail();
      if(!(CBORObject.FromObject(Integer.MAX_VALUE).CanTruncatedIntFitInInt32()))Assert.fail();
      if(CBORObject.FromObject(Double.POSITIVE_INFINITY).CanTruncatedIntFitInInt32())Assert.fail();
      if(CBORObject.FromObject(Double.NEGATIVE_INFINITY).CanTruncatedIntFitInInt32())Assert.fail();
      if(CBORObject.FromObject(Double.NaN).CanTruncatedIntFitInInt32())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.PositiveInfinity).CanTruncatedIntFitInInt32())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.NegativeInfinity).CanTruncatedIntFitInInt32())Assert.fail();
      if(CBORObject.FromObject(ExtendedDecimal.NaN).CanTruncatedIntFitInInt32())Assert.fail();
    }

    @Test
    public void TestIncompleteCBORString() {
      byte[] bytes = {  0x65, 0x41, 0x41, 0x41, 0x41  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestIncompleteIndefLengthArray() {
      byte[] bytes;
      bytes = new byte[] {  (byte)0x9f, 0, 0, 0, 0, 0  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0x9f, 0, 0, 0, 0, (byte)0xff  };
      try {
        CBORObject.DecodeFromBytes(bytes);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestIncompleteIndefLengthMap() {
      // Premature end after value
      byte[] bytes = {  (byte)0xbf, 0x61, 0x41, 0, 0x61, 0x42, 0  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      // Premature end after key
      bytes = new byte[] {  (byte)0xbf, 0x61, 0x41, 0, 0x61, 0x42  };
      try {
        CBORObject.DecodeFromBytes(bytes);
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  (byte)0xbf, 0x61, 0x41, 0, 0x61, 0x42, 0, (byte)0xff  };
      try {
        CBORObject.DecodeFromBytes(bytes);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestExtendedRationalArgValidation() {
      try {
        ExtendedRational.PositiveInfinity.ToBigIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedRational.NegativeInfinity.ToBigIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedRational.NaN.ToBigIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedRational.SignalingNaN.ToBigIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestExtendedDecimalArgValidation() {
      try {
        ExtendedDecimal.Create(null, BigInteger.ONE);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.Create(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.Create(BigInteger.ONE, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(ExtendedDecimal.Zero, ExtendedDecimal.FromString("0"));
      Assert.assertEquals(ExtendedDecimal.Zero, ExtendedDecimal.FromString("0", null));
      try {
        ExtendedDecimal.FromString(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString(null, 0, 1);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", -1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", 2, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", 0, -1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", 0, 2);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", 1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString(null, 0, 1, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", -1, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", 2, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", 0, -1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", 0, 2, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("x", 1, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        ExtendedFloat.Create(null, BigInteger.ONE);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.Create(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.Create(BigInteger.ONE, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(ExtendedFloat.Zero, ExtendedFloat.FromString("0"));
      Assert.assertEquals(ExtendedFloat.Zero, ExtendedFloat.FromString("0", null));
      try {
        ExtendedFloat.FromString(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString(null, 0, 1);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", -1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", 2, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", 0, -1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", 0, 2);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", 1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString(null, 0, 1, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", -1, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", 2, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", 0, -1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", 0, 2, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("x", 1, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("Infinity", PrecisionContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("-Infinity", PrecisionContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("NaN", PrecisionContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("sNaN", PrecisionContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("Infinity", PrecisionContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("-Infinity", PrecisionContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("NaN", PrecisionContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("sNaN", PrecisionContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        ExtendedDecimal.FromString("0..1");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("0.1x+222");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.FromString("0.1g-222");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("0..1");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("0.1x+222");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.FromString("0.1g-222");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestExtendedToInteger() {
      ExtendedDecimal dec = ExtendedDecimal.Create(999, -1);
      ExtendedFloat flo = ExtendedFloat.Create(999, -1);
      ExtendedRational rat = ExtendedRational.Create(8, 5);
      try {
        dec.ToBigIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        flo.ToBigIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        rat.ToBigIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        dec.ToBigInteger();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        flo.ToBigInteger();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        rat.ToBigInteger();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCyclicRefs() {
      CBORObject cbor = CBORObject.NewArray();
      cbor.Add(CBORObject.NewArray());
      cbor.Add(cbor);
      cbor.get(0).Add(cbor);
      try {
        cbor.WriteTo(new java.io.ByteArrayOutputStream());
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestNestingDepth() {
      try {
        java.io.ByteArrayOutputStream ms=null;
try {
ms=new java.io.ByteArrayOutputStream();

          for (int i = 0; i < 2000; ++i) {
            // Write beginning of indefinite-length array
            ms.write((byte)0x9f);
          }
          for (int i = 0; i < 2000; ++i) {
            // Write end of indefinite-length array
            ms.write((byte)0xff);
          }
          // Assert throwing CBOR exception for reaching maximum
          // nesting depth
          try {
            CBORObject.DecodeFromBytes(ms.toByteArray());
            Assert.fail("Should have failed");
          } catch (CBORException ex) {
          } catch (Exception ex) {
            Assert.fail(ex.toString());
            throw new IllegalStateException("", ex);
          }
}
finally {
try { if(ms!=null)ms.close(); } catch (java.io.IOException ex){}
}
        java.io.ByteArrayOutputStream ms2=null;
try {
ms2=new java.io.ByteArrayOutputStream();

          for (int i = 0; i < 495; ++i) {
            // Write beginning of indefinite-length array
            ms2.write((byte)0x9f);
          }
          for (int i = 0; i < 495; ++i) {
            // Write end of indefinite-length array
            ms2.write((byte)0xff);
          }
          // Maximum nesting depth not reached, so shouldn't throw
          try {
            CBORObject.DecodeFromBytes(ms2.toByteArray());
          } catch (Exception ex) {
            Assert.fail(ex.toString());
            throw new IllegalStateException("", ex);
          }
}
finally {
try { if(ms2!=null)ms2.close(); } catch (java.io.IOException ex){}
}
      } catch (Exception ex) {
        throw new IllegalStateException(ex.getMessage(), ex);
      }
    }

    @Test
    public void TestCBORBigInteger() {
      BigInteger bi = BigInteger.valueOf(Long.MAX_VALUE);
      bi=bi.add(BigInteger.ONE);
      try {
        CBORObject.FromObject(bi).AsInt64();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.FromObject(bi).AsInt32();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bi = BigInteger.valueOf(Long.MIN_VALUE);
      bi=bi.subtract(BigInteger.ONE);
      try {
        CBORObject.FromObject(bi).AsInt64();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.FromObject(bi).AsInt32();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bi = BigInteger.valueOf(Long.MIN_VALUE);
      try {
        CBORObject.FromObject(bi).AsInt32();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestEquivalentInfinities() {
      CBORObject co, co2;
      co = CBORObject.FromObject(ExtendedDecimal.PositiveInfinity);
      co2 = CBORObject.FromObject(Double.POSITIVE_INFINITY);
      Assert.assertEquals(0, co.compareTo(co2));
      Assert.assertEquals(0, co2.compareTo(co));
      co = CBORObject.NewMap().Add(ExtendedDecimal.PositiveInfinity, CBORObject.Undefined);
      co2 = CBORObject.NewMap().Add(Double.POSITIVE_INFINITY, CBORObject.Undefined);
      Assert.assertEquals(0, co.compareTo(co2));
      Assert.assertEquals(0, co2.compareTo(co));
    }

    @Test
    public void TestSharedRefs() {
      byte[] bytes;
      CBORObject cbor;
      String expected;
      bytes = new byte[] {  (byte)0x9f, (byte)0xd8, 28, 1, (byte)0xd8, 29, 0, 3, 3, (byte)0xd8, 29, 0, (byte)0xff  };
      cbor = CBORObject.DecodeFromBytes(bytes);
      expected = "[1,1,3,3,1]";
      Assert.assertEquals(expected, cbor.ToJSONString());
      bytes = new byte[] {  (byte)0x9f, (byte)0xd8, 28, (byte)0x81, 1, (byte)0xd8, 29, 0, 3, 3, (byte)0xd8, 29, 0, (byte)0xff  };
      cbor = CBORObject.DecodeFromBytes(bytes);
      expected = "[[1],[1],3,3,[1]]";
      Assert.assertEquals(expected, cbor.ToJSONString());
      // Checks if both objects are the same reference, not just equal
      if(!(cbor.get(0) == cbor.get(1)))Assert.fail( "cbor.get(0) not same as cbor.get(1)");
      if(!(cbor.get(0) == cbor.get(4)))Assert.fail( "cbor.get(0) not same as cbor.get(4)");
      bytes = new byte[] {  (byte)0xd8, 28, (byte)0x82, 1, (byte)0xd8, 29, 0  };
      cbor = CBORObject.DecodeFromBytes(bytes);
      Assert.assertEquals(2, cbor.size());
      // Checks if both objects are the same reference, not just equal
      if(!(cbor == cbor.get(1)))Assert.fail( "objects not the same");
    }

    @Test
    public void TestRationalCompareDecimal() {
      FastRandom fr = new FastRandom();
      // System.Diagnostics.Stopwatch sw1 = new System.Diagnostics.Stopwatch();
      // System.Diagnostics.Stopwatch sw2 = new System.Diagnostics.Stopwatch();
      for (int i = 0; i < 100; ++i) {
        ExtendedRational er = CBORTest.RandomRational(fr);
        int exp = -100000 + fr.NextValue(200000);
        ExtendedDecimal ed = ExtendedDecimal.Create(
          CBORTest.RandomBigInteger(fr),
          BigInteger.valueOf(exp));
        ExtendedRational er2 = ExtendedRational.FromExtendedDecimal(ed);
        // sw1.Start();
        int c2r = er.compareTo(er2);
        // sw1.Stop();sw2.Start();
        int c2d = er.CompareToDecimal(ed);
        // sw2.Stop();
        Assert.assertEquals(c2r, c2d);
      }
      // System.out.println("compareTo: " + (sw1.getElapsedMilliseconds()/1000.0) + " s");
      // System.out.println("CompareToDecimal: " + (sw2.getElapsedMilliseconds()/1000.0) + " s");
    }

    @Test
    public void TestRationalDivide() {
      FastRandom fr = new FastRandom();
      for (int i = 0; i < 100; ++i) {
        ExtendedRational er = CBORTest.RandomRational(fr);
        ExtendedRational er2 = CBORTest.RandomRational(fr);
        if (er2.signum()==0 || !er2.isFinite()) {
          continue;
        }
        ExtendedRational ermult = er.Multiply(er2);
        ExtendedRational erdiv = ermult.Divide(er);
        if (erdiv.compareTo(er2) != 0) {
          Assert.fail(er + "; " + er2);
        }
        erdiv = ermult.Divide(er2);
        if (erdiv.compareTo(er) != 0) {
          Assert.fail(er + "; " + er2);
        }
      }
    }

    @Test
    public void TestRationalRemainder() {
      FastRandom fr = new FastRandom();
      for (int i = 0; i < 100; ++i) {
        ExtendedRational er;
        ExtendedRational er2;
        er = new ExtendedRational(CBORTest.RandomBigInteger(fr), BigInteger.ONE);
        er2 = new ExtendedRational(CBORTest.RandomBigInteger(fr), BigInteger.ONE);
        if (er2.signum()==0 || !er2.isFinite()) {
          continue;
        }
        if (er.signum()==0 || !er.isFinite()) {
          // Code below will divide by "er",
          // so skip if "er" is zero
          continue;
        }
        ExtendedRational ermult = er.Multiply(er2);
        ExtendedRational erdiv = ermult.Divide(er);
        erdiv = ermult.Remainder(er);
        if (erdiv.signum()!=0) {
          Assert.fail(ermult + "; " + er);
        }
        erdiv = ermult.Remainder(er2);
        if (erdiv.signum()!=0) {
          Assert.fail(er + "; " + er2);
        }
      }
    }

    @Test
    public void TestRationalCompare() {
      FastRandom fr = new FastRandom();
      for (int i = 0; i < 100; ++i) {
        BigInteger num = CBORTest.RandomBigInteger(fr);
        if (num.signum()==0) {
          // Skip if number is 0; 0/1 and 0/2 are
          // equal in that case
          continue;
        }
        num = (num).abs();
        ExtendedRational rat = new ExtendedRational(num, BigInteger.ONE);
        ExtendedRational rat2 = new ExtendedRational(num, BigInteger.valueOf(2));
        if (rat2.compareTo(rat) != -1) {
          Assert.fail(rat + "; " + rat2);
        }
        if (rat.compareTo(rat2) != 1) {
          Assert.fail(rat + "; " + rat2);
        }
      }
      Assert.assertEquals(
        -1,
        new ExtendedRational(BigInteger.ONE, BigInteger.valueOf(2)).compareTo(
          new ExtendedRational(BigInteger.valueOf(4), BigInteger.ONE)));
      for (int i = 0; i < 100; ++i) {
        BigInteger num = CBORTest.RandomBigInteger(fr);
        BigInteger den = CBORTest.RandomBigInteger(fr);
        if (den.signum()==0) {
          den = BigInteger.ONE;
        }
        ExtendedRational rat = new ExtendedRational(num, den);
        for (int j = 0; j < 10; ++j) {
          BigInteger num2 = num;
          BigInteger den2 = den;
          BigInteger mult = CBORTest.RandomBigInteger(fr);
          if (mult.signum()==0 || mult.equals(BigInteger.ONE)) {
            mult = BigInteger.valueOf(2);
          }
          num2=num2.multiply(mult);
          den2=den2.multiply(mult);
          ExtendedRational rat2 = new ExtendedRational(num2, den2);
          if (rat.compareTo(rat2) != 0) {
            Assert.assertEquals(rat + "; " + rat2 + "; " + rat.ToDouble() + "; " + rat2.ToDouble(),0,rat.compareTo(rat2));
          }
        }
      }
    }

    @Test
    public void TestBuiltInTags() {
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc2, 0x00  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc2, 0x20  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc2, 0x60  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc2, (byte)0x80  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc2, (byte)0xa0  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc2, (byte)0xe0  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x00  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x20  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x60  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, (byte)0x80  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, (byte)0xa0  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, (byte)0xe0  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, 0x00  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, 0x20  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, 0x40  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, 0x60  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, (byte)0x80  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xd8, 0x1e, (byte)0x9f, 0x01, 0x01, (byte)0xff  });
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xd8, 0x1e, (byte)0x9f, 0x01, (byte)0xff  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xd8, 0x1e, (byte)0x9f, (byte)0xff  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, (byte)0x9f, 0x00, 0x00, (byte)0xff  });
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, (byte)0x9f, 0x00, 0x00, (byte)0xff  });
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, (byte)0x9f, 0x00, (byte)0xff  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, (byte)0x9f, 0x00, (byte)0xff  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, (byte)0x9f, (byte)0xff  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, (byte)0x9f, (byte)0xff  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, (byte)0x81, 0x00  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, (byte)0xa0  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc4, (byte)0xe0  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, 0x00  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, 0x20  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, 0x40  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, 0x60  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, (byte)0x80  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, (byte)0x81, 0x00  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, (byte)0xa0  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc5, (byte)0xe0  });
        Assert.fail("Should have failed");
      } catch (CBORException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      Assert.assertEquals(BigInteger.ZERO, CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc2, 0x40  }).AsBigInteger());
      Assert.assertEquals(BigInteger.ZERO.subtract(BigInteger.ONE), CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x41, 0x00  }).AsBigInteger());
      Assert.assertEquals(BigInteger.ZERO.subtract(BigInteger.ONE), CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x40  }).AsBigInteger());
    }

    @Test
    public void TestUUID() {
      CBORObject obj = CBORObject.FromObject(java.util.UUID.fromString("00112233-4455-6677-8899-AABBCCDDEEFF"));
      Assert.assertEquals(CBORType.ByteString, obj.getType());
      Assert.assertEquals(BigInteger.valueOf(37), obj.getInnermostTag());
      byte[] bytes = obj.GetByteString();
      Assert.assertEquals(16, bytes.length);
      Assert.assertEquals(0x00, bytes[0]);
      Assert.assertEquals(0x11, bytes[1]);
      Assert.assertEquals(0x22, bytes[2]);
      Assert.assertEquals(0x33, bytes[3]);
      Assert.assertEquals(0x44, bytes[4]);
      Assert.assertEquals(0x55, bytes[5]);
      Assert.assertEquals(0x66, bytes[6]);
      Assert.assertEquals(0x77, bytes[7]);
      Assert.assertEquals((byte)0x88, bytes[8]);
      Assert.assertEquals((byte)0x99, bytes[9]);
      Assert.assertEquals((byte)0xaa, bytes[10]);
      Assert.assertEquals((byte)0xbb, bytes[11]);
      Assert.assertEquals((byte)0xcc, bytes[12]);
      Assert.assertEquals((byte)0xdd, bytes[13]);
      Assert.assertEquals((byte)0xee, bytes[14]);
      Assert.assertEquals((byte)0xff, bytes[15]);
    }

    // @Test
    public static void TestMiniCBOR() {
      byte[] bytes;
      bytes = new byte[] {  0x19, 2  };
      try {
        MiniCBOR.ReadInt32(new java.io.ByteArrayInputStream(bytes));
        Assert.fail("Should have failed");
      } catch (IOException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  0x1a, 2  };
      try {
        MiniCBOR.ReadInt32(new java.io.ByteArrayInputStream(bytes));
        Assert.fail("Should have failed");
      } catch (IOException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  0x1b, 2  };
      try {
        MiniCBOR.ReadInt32(new java.io.ByteArrayInputStream(bytes));
        Assert.fail("Should have failed");
      } catch (IOException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  0x1b, 2, 2, 2, 2, 2, 2, 2, 2  };
      try {
        MiniCBOR.ReadInt32(new java.io.ByteArrayInputStream(bytes));
        Assert.fail("Should have failed");
      } catch (IOException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      bytes = new byte[] {  0x1c, 2  };
      try {
        MiniCBOR.ReadInt32(new java.io.ByteArrayInputStream(bytes));
        Assert.fail("Should have failed");
      } catch (IOException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        bytes = new byte[] {  0  };
        java.io.ByteArrayInputStream ms=null;
try {
ms=new java.io.ByteArrayInputStream(bytes);

          Assert.assertEquals(0, MiniCBOR.ReadInt32(ms));
}
finally {
try { if(ms!=null)ms.close(); } catch (java.io.IOException ex){}
}
        bytes = new byte[] {  0x17  };
        java.io.ByteArrayInputStream ms2=null;
try {
ms2=new java.io.ByteArrayInputStream(bytes);

          Assert.assertEquals(0x17, MiniCBOR.ReadInt32(ms2));
}
finally {
try { if(ms2!=null)ms2.close(); } catch (java.io.IOException ex){}
}
        bytes = new byte[] {  0x18, 2  };
        java.io.ByteArrayInputStream ms3=null;
try {
ms3=new java.io.ByteArrayInputStream(bytes);

          Assert.assertEquals(2, MiniCBOR.ReadInt32(ms3));
}
finally {
try { if(ms3!=null)ms3.close(); } catch (java.io.IOException ex){}
}
        bytes = new byte[] {  0x19, 0, 2  };
        java.io.ByteArrayInputStream ms4=null;
try {
ms4=new java.io.ByteArrayInputStream(bytes);

          Assert.assertEquals(2, MiniCBOR.ReadInt32(ms4));
}
finally {
try { if(ms4!=null)ms4.close(); } catch (java.io.IOException ex){}
}
        bytes = new byte[] {  0x27  };
        java.io.ByteArrayInputStream ms5=null;
try {
ms5=new java.io.ByteArrayInputStream(bytes);

          Assert.assertEquals(-1 - 7, MiniCBOR.ReadInt32(ms5));
}
finally {
try { if(ms5!=null)ms5.close(); } catch (java.io.IOException ex){}
}
        bytes = new byte[] {  0x37  };
        java.io.ByteArrayInputStream ms6=null;
try {
ms6=new java.io.ByteArrayInputStream(bytes);

          Assert.assertEquals(-1 - 0x17, MiniCBOR.ReadInt32(ms6));
}
finally {
try { if(ms6!=null)ms6.close(); } catch (java.io.IOException ex){}
}
      } catch (IOException ioex) {
        Assert.fail(ioex.getMessage());
      }
    }

    @Test
    public void TestNegativeBigInts() {
      BigInteger minusone = BigInteger.ZERO.subtract(BigInteger.ONE);
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(8)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x42, 1, 0  }).AsBigInteger());
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(16)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x43, 1, 0, 0  }).AsBigInteger());
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(24)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x44, 1, 0, 0, 0  }).AsBigInteger());
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(32)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x45, 1, 0, 0, 0, 0  }).AsBigInteger());
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(40)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x46, 1, 0, 0, 0, 0, 0  }).AsBigInteger());
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(48)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x47, 1, 0, 0, 0, 0, 0, 0  }).AsBigInteger());
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(56)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x48, 1, 0, 0, 0, 0, 0, 0, 0  }).AsBigInteger());
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(64)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x49, 1, 0, 0, 0, 0, 0, 0, 0, 0  }).AsBigInteger());
      Assert.assertEquals(
        minusone .subtract(BigInteger.ONE.shiftLeft(72)),
        CBORObject.DecodeFromBytes(new byte[] {  (byte)0xc3, 0x4a, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0  }).AsBigInteger());
    }

    @Test
    public void TestStringRefs() {
      CBORObject cbor = CBORObject.DecodeFromBytes(
        new byte[] {  (byte)0xd9,
        1,
        0,
        (byte)0x9f,
        0x64,
        0x61,
        0x62,
        0x63,
        0x64,
        (byte)0xd8,
        0x19,
        0x00,
        (byte)0xd8,
        0x19,
        0x00,
        0x64,
        0x62,
        0x62,
        0x63,
        0x64,
        (byte)0xd8,
        0x19,
        0x01,
        (byte)0xd8,
        0x19,
        0x00,
        (byte)0xd8,
        0x19,
        0x01,
        (byte)0xff  });
      String expected = "[\"abcd\",\"abcd\",\"abcd\",\"bbcd\",\"bbcd\",\"abcd\",\"bbcd\"]";
      Assert.assertEquals(expected, cbor.ToJSONString());
      cbor = CBORObject.DecodeFromBytes(
        new byte[] {  (byte)0xd9,
        1,
        0,
        (byte)0x9f,
        0x64,
        0x61,
        0x62,
        0x63,
        0x64,
        0x62,
        0x61,
        0x61,
        (byte)0xd8,
        0x19,
        0x00,
        (byte)0xd8,
        0x19,
        0x00,
        0x64,
        0x62,
        0x62,
        0x63,
        0x64,
        (byte)0xd8,
        0x19,
        0x01,
        (byte)0xd8,
        0x19,
        0x00,
        (byte)0xd8,
        0x19,
        0x01,
        (byte)0xff  });
      expected = "[\"abcd\",\"aa\",\"abcd\",\"abcd\",\"bbcd\",\"bbcd\",\"abcd\",\"bbcd\"]";
      Assert.assertEquals(expected, cbor.ToJSONString());
    }

    @Test
    public void TestExtendedNaNZero() {
      if(ExtendedDecimal.NaN.signum()==0)Assert.fail();
      if(ExtendedDecimal.SignalingNaN.signum()==0)Assert.fail();
      if(ExtendedFloat.NaN.signum()==0)Assert.fail();
      if(ExtendedFloat.SignalingNaN.signum()==0)Assert.fail();
      if(ExtendedRational.NaN.signum()==0)Assert.fail();
      if(ExtendedRational.SignalingNaN.signum()==0)Assert.fail();
    }

    @Test
    public void TestToBigIntegerNonFinite() {
      try {
        ExtendedDecimal.PositiveInfinity.ToBigInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.NegativeInfinity.ToBigInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.NaN.ToBigInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedDecimal.SignalingNaN.ToBigInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.PositiveInfinity.ToBigInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.NegativeInfinity.ToBigInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.NaN.ToBigInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ExtendedFloat.SignalingNaN.ToBigInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
  }